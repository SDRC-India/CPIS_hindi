/*
 * Author Sourav keshari nath
*/
myAppConstructor.controller('PaginationController', PaginationController)
function PaginationController($scope) {
	
  $scope.pageChangeHandler = function(num) {
    console.log('going to page ' + num);
  };
}
 