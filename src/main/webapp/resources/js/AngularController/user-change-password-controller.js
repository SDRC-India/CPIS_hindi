var app = angular.module("userChangePassApp", []);



app.controller("UserChangePassController", function($scope, $http, $location, $timeout, $window){
	
	$scope.email = $location.absUrl().split("resetPassword")[1];
	$("#nomatch").hide();
	$scope.submit = function(){
		if($scope.newpassword == $scope.reenteredpassword){
			if($scope.newpassword.length >= 8){
				$http({
					method: "POST",
				 	url: 'newPassword?password='+$scope.newpassword
						+'&email='+encodeURIComponent($scope.email)
				})
				.then(function (response) {
					console.log(response.data);
					$scope.newpassword=null;
					$scope.reenteredpassword=null;
					if(response.data == "success"){
						$('#changePasswordModal').modal('show');
					}
				});
			}
			else{
				$("#min8").show();
			}
		}
		else{
			$("#nomatch").show();
			document.getElementById('passwordError').innerHTML="Password doesn't match.";
		}
	};
	
	$scope.changeUserPassword = function(){
		if($scope.newpassword == $scope.reenteredpassword){
			if($scope.newpassword.length >= 8){
				$http({
					method: "POST",
				 	url: 'changeToNewPassword?password='+$scope.newpassword
				})
				.then(function (response) {
					console.log(response.data);
					$scope.newpassword=null;
					$scope.reenteredpassword=null;
					if(response.data == "success"){
						$('#changePasswordModal').modal('show');
					}
				});
			}
			else{
				$("#min8").show();
			}
		}
		else{
			$("#nomatch").show();
			document.getElementById('passwordError').innerHTML="Password doesn't match.";
		}
	};
	
	$scope.changeUserPw = function(){
		$scope.changePasswordMsg="";
		$scope.changePasswordStatus=true;
		$scope.email = $location.absUrl().split("email=")[1];;
		if($scope.newpassword == $scope.reenteredpassword && $scope.newemail!=undefined){
			if($scope.newpassword.length >= 8){
				$http({
					method: "POST",
					url: 'changeUserPassword?password='+$scope.newpassword
						+'&email='+$scope.email+'&newEmail='+$scope.newemail
				})
//				$http.post('/newPassword?password='+$scope.newpassword
//						+'&email='+encodeURIComponent($scope.email))
				.then(function (response) {
					console.log(response.data);
					$scope.newemail=null;
					$scope.newpassword=null;
					$scope.reenteredpassword=null;
					if(response.data == "success"){
						$scope.changePasswordStatus=true;
						$scope.changePasswordMsg="Email and Password changed successfully."
					}else if(response.data == "no_user"){
						$scope.changePasswordStatus=false;
						$scope.changePasswordMsg="Please provide a registered email."
					}else if(response.data == "email_exists"){
						$scope.changePasswordStatus=false;
						$scope.changePasswordMsg="This email is aready registered. Please enter a new email."
					}
					$('#changeUserPwModal').modal('show');
//					else{
//						$("#change-failure").show().delay(2000).fadeOut("slow");
//					}
				});
			}
			else{
				$("#min8").show().delay(2000).fadeOut("slow");
			}
		}
		else{
			$("#nomatch").show().delay(2000).fadeOut("slow");
		}
	
	};
	$scope.reDirect = function(){
		if($scope.changePasswordStatus==true)
			$window.location.href = '/CPIS/userList';
	};
	$scope.reDirectCctsLogin = function(){
		$window.location.href = '/CPIS/ccts_login';
	};
	$scope.newUser = {};
	$scope.validatePassword = function(reenteredpassword){
		
			if($scope.newpassword != reenteredpassword){
				document.getElementById('passwordError').innerHTML = "Password does not match";
			}else{
				document.getElementById('passwordError').innerHTML = "";
			}
		
	};
});


(function($) {
    $(document).ready(function() {
        $('input').blur(function() {
            var value = $.trim( $(this).val() );
            $(this).val( value );
        });
    });
})(jQuery);