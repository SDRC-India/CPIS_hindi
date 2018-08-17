<div class="col-lg-3 col-md-3 col-sm-6 four-col-box" ng-controller="noticeCtrl" >
      <div class="ca-hover" >
        <div class="carousel-content notice_board">
          <h3 >{{title}}</h3>
         
            
       	     <ul ticker >
			  <li ng-repeat="item in noticeData">
			   {{item.content}}
			    
			  </li>
			</ul>
        </div>
      </div>
    </div>