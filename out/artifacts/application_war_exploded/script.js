angular.module("winnieApp", []);
angular.module("winnieApp").controller("winnieCtrl", ['$scope', function($scope){

  $scope.information = [{'id': 1001, 'name': 'Winnie', 'gender': 'Male', 'birthday': '1926-12-24', 'phone': '0936-XXX-XXX', 'createdtime': '2016-03-11 10:56:38'},
                        {'id': 1002, 'name': 'Piglet', 'gender': 'Female', 'birthday': '1938-03-28', 'phone': '0978-XXX-XXX', 'createdtime': '2016-05-26 16:17:22'},
                        {'id': 1003, 'name': 'Tigger', 'gender': 'Male', 'birthday': '1935-12-06', 'phone': '0956-XXX-XXX', 'createdtime': '2016-07-27 18:20:58'}];
	$scope.info = {};
	$scope.listInfo = false;
	$scope.infoID = 1004;
	$scope.undoHistory = [];
	$scope.redoHistory = [];

	// -----新增-----
	$scope.addInfo = function(){
		$scope.listInfo = true;
		$scope.info = {
			'panelTitle': 'Add Information',
			'id': $scope.infoID,
			'name': 'Rabbit', 
			'gender': 'Male', 
			'birthday': '1942-06-17', 
			'phone': '0986-XXX-XXX'
		};
	};

	// -----編輯-----
	$scope.editInfo = function(data){
		$scope.listInfo = true;
		$scope.info = {
			'panelTitle': 'Edit Information',
			'id': data.id,
			'name': data.name, 
			'gender': data.gender, 
			'birthday': data.birthday, 
			'phone': data.phone
		};
	};

	// -----新增/編輯 資料加入陣列-----
	$scope.summitInfo = function(){
		var date = new Date(),
			year = date.getFullYear(),
			month = date.getMonth()+1 >= 10 ? date.getMonth()+1 : '0'+ (date.getMonth()+1),
			day = date.getDate() >= 10 ? date.getDate() : '0'+ date.getDate(),
			hours = date.getHours() >= 10 ? date.getHours() : '0'+ date.getHours(),
			minutes = date.getMinutes() >= 10 ? date.getMinutes() : '0'+ date.getMinutes(),
			seconds = date.getSeconds() >= 10 ? date.getSeconds() : '0'+ date.getSeconds(),
			now = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
		
		if($scope.info.panelTitle == 'Add Information'){
			$scope.undoHistory.push(angular.copy($scope.information));
			$scope.information.push({
				'id': $scope.info.id,
				'name': $scope.info.name, 
				'gender': $scope.info.gender, 
				'birthday': $scope.info.birthday, 
				'phone': $scope.info.phone,
				'createdtime': now,
			});
			$scope.listInfo = false;
			$scope.infoID = $scope.infoID + 1;
		}else{
			$scope.undoHistory.push(angular.copy($scope.information));
			angular.forEach($scope.information, function(value, key){
				if(value.id == $scope.info.id){
					value.name = $scope.info.name;
					value.gender = $scope.info.gender;
					value.birthday = $scope.info.birthday;
					value.phone = $scope.info.phone;
				}
			});
			$scope.listInfo = false;
		}
	};

	// -----刪除-----
	$scope.deleteInfo = function(data){
		$scope.undoHistory.push(angular.copy($scope.information));
		$scope.information.splice($scope.information.indexOf(data), 1);
	};

	// -----批次/全部刪除-----
	$scope.deleteSelInfo = function(){
		$scope.undoHistory.push(angular.copy($scope.information));
		if($scope.checkAll){
			$scope.information = [];
			$scope.checkAll = false;
		}else{
			angular.forEach($scope.information, function(value, key){
				if(value.check){
					$scope.information.splice($scope.information.indexOf(value), 1);
				}
			});
		}
	};

	// -----取消-----
	$scope.cancelInfo = function(){
		$scope.info = {};
		$scope.listInfo = false;
	};

	// -----取消上一步-----
	$scope.undoInfo = function(){
		if($scope.undoHistory.length){
			$scope.redoHistory.push(angular.copy($scope.information)); 
			$scope.information = $scope.undoHistory.pop();
		}
	};

	// -----恢復上一步-----
	$scope.redoInfo = function(){
		if($scope.redoHistory.length){
			$scope.undoHistory.push(angular.copy($scope.information));
			$scope.information = $scope.redoHistory.pop();
		}
	};

}]);