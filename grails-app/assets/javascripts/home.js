function HomeCtrl($scope, $http) {

	$scope.init = function() {
		
	}

	$scope.formSubmit = function() {
		var o = {
			name: 'ben',
			dob: '2014-09-15'
		};

		var url = '/home/add';
		$http.post(url, o).success(function(data) {
			console.dir(data)
		});
	}
}

app.controller('HomeCtrl', ['$scope', '$http', HomeCtrl]);