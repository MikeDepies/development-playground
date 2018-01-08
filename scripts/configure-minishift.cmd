@FOR /f "tokens=*" %i IN ('minishift oc-env') DO @call %i
@FOR /f "tokens=*" %i IN ('minishift docker-env') DO @call %i