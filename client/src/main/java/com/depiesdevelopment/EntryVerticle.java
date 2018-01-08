package com.depiesdevelopment;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;

public class EntryVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.rxDeployVerticle(DashboardVerticle.class.getName());
        vertx.rxDeployVerticle(DataUploadVerticle.class.getName());
    }
}
