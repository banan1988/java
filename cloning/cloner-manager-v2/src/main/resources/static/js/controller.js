app.controller('clustersController', function($scope, $http) {
    $scope.headingTitle = "HTTP Cloner Manager v2";
    $http.get('/v2/clusters').
            then(function(response) {
                $scope.clusters = response.data;
            });

//    var percentColors = [
//        { pct: 0.0, color: { r: 0xff, g: 0x00, b: 0 } },
//        { pct: 0.5, color: { r: 0xff, g: 0xff, b: 0 } },
//        { pct: 1.0, color: { r: 0x00, g: 0xff, b: 0 } } ];
    var percentColors = [
        { pct: 0.0, color: { r: 0xd9, g: 0x53, b: 0x4f } },
        { pct: 0.5, color: { r: 0xec, g: 0x97, b: 0x1f } },
        { pct: 1.0, color: { r: 0x5c, g: 0xb8, b: 0x5c } } ];

    var colorForPercentage = function(pct) {
        for (var i = 1; i < percentColors.length - 1; i++) {
            if (pct < percentColors[i].pct) {
                break;
            }
        }
        var lower = percentColors[i - 1];
        var upper = percentColors[i];
        var range = upper.pct - lower.pct;
        var rangePct = (pct - lower.pct) / range;
        var pctLower = 1 - rangePct;
        var pctUpper = rangePct;
        var color = {
            r: Math.floor(lower.color.r * pctLower + upper.color.r * pctUpper),
            g: Math.floor(lower.color.g * pctLower + upper.color.g * pctUpper),
            b: Math.floor(lower.color.b * pctLower + upper.color.b * pctUpper)
        };
        return 'rgb(' + [color.r, color.g, color.b].join(',') + ')';
        // or output as hex if preferred
    }

    $scope.getTrafficRate = function(trafficRate) {
        return trafficRate*100 + "%";
    };

    $scope.getTrafficRateColor = function(trafficRate) {
        var backgroundColor = "background-color: " + colorForPercentage(trafficRate);
        var color = "color: #fff"
        return backgroundColor + ";" + color;
    };

    $scope.getFullHostDomain = function(host) {
        var hostDomain = host.hostDomain.host + "." + host.hostDomain.domain;
        var application = host.application.listenPort + host.application.contextPath;
        return "http://" + hostDomain + ":" + application;
    };

    $scope.isEnabled = function(enabled) {
        if (enabled) {
            return "label-success";
        }
        return "label-default";
    };
});

app.controller('addClustersController', function($scope, $http) {
    $scope.headingTitle = "HTTP Cloner Manager v2";


});

app.controller('addClustersHostController', function($scope, $http) {
    $scope.headingTitle = "HTTP Cloner Manager v2";


  $scope.peopleObj = {
      '1' : { name: 'Adam',      email: 'adam@email.com',      age: 12, country: 'United States' },
      '2' : { name: 'Amalie',    email: 'amalie@email.com',    age: 12, country: 'Argentina' },
      '3' : { name: 'Estefanía', email: 'estefania@email.com', age: 21, country: 'Argentina' },
      '4' : { name: 'Adrian',    email: 'adrian@email.com',    age: 21, country: 'Ecuador' },
      '5' : { name: 'Wladimir',  email: 'wladimir@email.com',  age: 30, country: 'Ecuador' },
      '6' : { name: 'Samantha',  email: 'samantha@email.com',  age: 30, country: 'United States' },
      '7' : { name: 'Nicole',    email: 'nicole@email.com',    age: 43, country: 'Colombia' },
      '8' : { name: 'Natasha',   email: 'natasha@email.com',   age: 54, country: 'Ecuador' },
      '9' : { name: 'Michael',   email: 'michael@email.com',   age: 15, country: 'Colombia' },
      '10' : { name: 'Nicolás',   email: 'nicolas@email.com',    age: 43, country: 'Colombia' }
    };

    $scope.person = {};

    $scope.person.selectedValue = $scope.peopleObj[3];
    $scope.person.selectedSingle = 'Samantha';
    $scope.person.selectedSingleKey = '5';
    // To run the demos with a preselected person object, uncomment the line below.
    //vm.person.selected = vm.person.selectedValue;

  $scope.people = [
    { name: 'Adam',      email: 'adam@email.com',      age: 12, country: 'United States' },
    { name: 'Amalie',    email: 'amalie@email.com',    age: 12, country: 'Argentina' },
    { name: 'Estefanía', email: 'estefania@email.com', age: 21, country: 'Argentina' },
    { name: 'Adrian',    email: 'adrian@email.com',    age: 21, country: 'Ecuador' },
    { name: 'Wladimir',  email: 'wladimir@email.com',  age: 30, country: 'Ecuador' },
    { name: 'Samantha',  email: 'samantha@email.com',  age: 30, country: 'United States' },
    { name: 'Nicole',    email: 'nicole@email.com',    age: 43, country: 'Colombia' },
    { name: 'Natasha',   email: 'natasha@email.com',   age: 54, country: 'Ecuador' },
    { name: 'Michael',   email: 'michael@email.com',   age: 15, country: 'Colombia' },
    { name: 'Nicolás',   email: 'nicolas@email.com',    age: 43, country: 'Colombia' }
  ];
});
