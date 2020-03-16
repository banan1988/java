var app = angular.module('app', ['ngRoute', 'ngResource', 'spring-data-rest', 'ngSanitize', 'ui.select']);

app.config(function($routeProvider){
    $routeProvider
        .when('/clusters',{
            templateUrl: '/views/clusters.html',
            controller: 'clustersController'
        })
        .when('/clusters/add',{
            templateUrl: '/views/clusters_add.html',
            controller: 'addClustersController'
        }).when('/clusters/xxx/hosts/add',{
            templateUrl: '/views/clusters_host_add.html',
            controller: 'addClustersHostController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
