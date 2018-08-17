
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" ng-app="upcpisApp">
<head>
<style>
.c1{
     margin-bottom:15px;
    }
</style>
 <jsp:include page="./common/header.jsp" />
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="galleryCtrl" ng-cloak>

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide">
  <div class="row">
   
    <div class="col-md-12" >
     <h3 class="page_title">{{imageTitle}}</h3>
     
   	   <div class="content">
   	      
			    <div class="row content c1">
			    <div class="col-md-3">
			     <a class="example-image-link " href="resources/img/gallery/Big_1.jpg" data-lightbox="example-set" data-title="Balgriha Balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_1.jpg" alt=""/></a>
			      </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_2.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_2.jpg" alt="" /></a>
			      </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_3.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_3.jpg" alt="" /></a>
			      </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_4.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_4.jpg" alt="" /></a>
			    </div>
			    </div>
			   
			    <div class="row structure-image c1">
			    <div class="col-md-3">
			     <a class="example-image-link " href="resources/img/gallery/Big_5.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_5.jpg" alt=""/></a>
			      </div
			      ><div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_6.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_6.jpg" alt="" /></a>
			      </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_7.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_7.jpg" alt="" /></a>
			      </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_8.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_8.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_9.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_9.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_10.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_10.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_11.jpg" data-lightbox="example-set" data-title="Balgriha balak,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_11.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_12.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_12.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_13.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_13.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_14.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_14.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_15.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_15.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_16.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_16.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_17.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_17.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_18.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_18.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_19.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_19.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_20.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_20.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_21.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_21.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_22.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_22.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_23.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_23.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_24.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_24.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_25.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_25.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_26.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_26.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_27.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_27.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_28.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_28.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_29.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_29.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_30.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_30.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_31.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_31.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_32.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_32.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_33.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_33.jpg" alt="" /></a>
			    </div>
			    
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_34.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_34.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_35.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_35.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_36.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_36.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_37.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_37.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_38.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_38.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_39.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_39.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_40.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_40.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_41.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_41.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_42.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_42.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_43.jpg" data-lightbox="example-set" data-title="Drusti Samajik Sanstan,Lucknow"><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_43.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_44.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_44.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_45.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_45.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_46.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_46.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_47.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_47.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_48.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_48.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_49.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_49.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_50.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_50.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_51.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_51.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_52.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_52.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_53.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_53.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_54.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_54.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_55.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_55.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_56.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_56.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_57.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_57.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_58.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_58.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_59.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_59.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_60.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_60.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_61.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_61.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_62.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_62.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_63.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_63.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_64.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_64.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_65.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_65.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_66.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_66.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_67.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_67.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_68.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_68.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_69.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_69.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_70.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_70.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_71.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_71.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_72.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_72.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_73.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_73.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_74.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_74.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_75.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_75.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_76.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_76.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_77.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_77.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_78.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_78.jpg" alt="" /></a>
			    </div>
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_79.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_79.jpg" alt="" /></a>
			    </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_80.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_80.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_81.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_81.jpg" alt="" /></a>
			    </div>
			       <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_82.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_82.jpg" alt="" /></a>
			    </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_83.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_83.jpg" alt="" /></a>
			    </div>
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_84.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_84.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			      <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_85.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_85.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_86.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_86.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_87.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_87.jpg" alt="" /></a>
			    </div>
			     <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_88.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_88.jpg" alt="" /></a>
			    </div>
			    </div>
			    <div class="row c1">
			    <div class="col-md-3">
			      <a class="example-image-link" href="resources/img/gallery/Big_89.jpg" data-lightbox="example-set" data-title="Nirvan Special Home."><img class="example-image img img-responsive" src="resources/img/gallery/thumbs/Thumb_89.jpg" alt="" /></a>
			    </div>
			    </div>
			    
	   </div>
   	</div>
    
    
  
  </div>
</div>
<!-- About Section End -->





<div class="container">
  <div class="row">
    <hr>
  </div>
</div>
<!-- Footer Section Starts-->
 <jsp:include page="./common/footer.jsp" />
<!-- Footer Section End-->
 <jsp:include page="./common/footer_scripts.jsp" />
</body>
</html>
