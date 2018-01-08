package com.depiesdevelopment;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.FileUpload;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.StaticHandler;
import io.vertx.reactivex.servicediscovery.ServiceDiscovery;

import java.util.Iterator;
import java.util.Set;

public class DashboardVerticle extends AbstractVerticle {

    private ServiceDiscovery discovery;
    private final Logger log = LoggerFactory.getLogger(DashboardVerticle.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        log.info("starting up Dash verticle");
        Router router = Router.router(vertx);

        router.get("/health").handler(rc -> rc.response().end("OK"));


        MetricRegistry dropwizardRegistry = SharedMetricRegistries.getOrCreate(
                System.getProperty("vertx.metrics.options.registryName")
        );
        ServiceDiscovery.create(vertx, sd -> {
            this.discovery = sd;
            router.get("/health").handler(rc -> rc.response().end("OK"));
            router.post("/upload").handler(BodyHandler.create().setMergeFormAttributes(true).setUploadsDirectory("/data"));
            router.post("/upload").handler(rc -> {
                log.info(rc.queryParams().toString());
                log.info(rc.get("file"));
                log.info(rc.getBodyAsString());
                int countOfFiles = rc.fileUploads().size();
                log.info("Upload request - " + countOfFiles + " files");
                Set<FileUpload> files = rc.fileUploads();
                Iterator<FileUpload> fitr = files.iterator();

                while(fitr.hasNext()) {
                    FileUpload fu = fitr.next();
                    log.info("file: " + fu.fileName());
                }

                rc.response().end(new JsonObject().put("fileCount", countOfFiles).encode());
            });
            router.route("/*").handler(StaticHandler.create("consoleroot"));
            vertx.createHttpServer().requestHandler(router::accept).rxListen(8080).subscribe();
            startFuture.complete();
        });
    }
}
