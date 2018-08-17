
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html lang="en" ng-app="upcpisApp">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
 <jsp:include page="./common/header.jsp" />
 <style>
 .Tab-list
 {
 margin:45px 0 10px;	
 }
    
 .inner-page-title {
    margin-bottom: 60px;
    position: relative;
}
.fa-icon
{
color: #080808;
}

.fa-icon:hover
{
color: orange;
}
.table-heading
{
font-size:18px;
}

 </style>
 </head>
<body data-spy="scroll" data-offset="20" data-target="#navbar" id="mymain" ng-controller="homeCtrl">

<!-- Nav Menu Section Start-->
 <jsp:include page="./common/nav.jsp" />
<!-- Nav Menu Section End -->

<!-- About Section -->
<div class="container contain-box row-slide" style="min-height: 400px;">
  <div class="row">
   
    <div class="col-md-12">
     <h3 class="page_title">Help</h3>
      <ul class="nav nav-tabs Tab-list">
    <li class="active"><a data-toggle="tab" href="#tab1">User Manual in English</a></li>
    <li><a data-toggle="tab" href="#tab2">User Manual in Hindi</a></li>
   
  </ul>
     
         <div class="tab-content" style="margin-top: 45px;">
       <div id="tab1" class="tab-pane fade in active">
        
         <table class="table table-striped">
            <thead>
              <tr>
                <th class="table-heading">Particular</th>
                <th class="table-heading">Download</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Admin User Manual</td>
                <td><a href="resources/English Pdf/CPIS_Admin_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>CCI User Manual</td>
                <td><a href="resources/English Pdf/CPIS_CCI_USER_MANUAL_final_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>CWC User Manual</td>
                <td><a href="resources/English Pdf/CPIS_CWC_USER_MANUAL_r3......pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>DCPU User Manual</td>
                <td><a href="resources/English Pdf/CPIS_DCPU_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>Director User Manual</td>
                <td><a href="resources/English Pdf/CPIS_Director_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>DPO User Manual</td>
                <td><a href="resources/English Pdf/CPIS_DPO_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>Dy.CPO User Manual</td>
                <td><a href="resources/English Pdf/CPIS_Dy. CPO_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>JJB User Manual</td>
                <td><a href="resources/English Pdf/CPIS_JJB_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>Principal Secretary User Manual</td>
                <td><a href="resources/English Pdf/CPIS_Principal Secretary_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>SCPS User Manual</td>
                <td><a href="resources/English Pdf/CPIS_SCPS_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>SJPU User Manual</td>
                <td><a href="resources/English Pdf/CPIS_SJPU_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>

           </tbody>
          </table>
              
              
              
              
      
    </div>
    <div id="tab2" class="tab-pane">
        
         <table class="table table-striped">
            <thead>
              <tr>
                <th class="table-heading">Particular</th>
                <th class="table-heading">Download</th>
              </tr>
            </thead>
            <tbody> 
              <tr>
                <td>उपयोगकर्ता गाइड(व्यवस्थापक)</td>
                <td><a href="resources/hindi pdf/CPIS_Admin_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>उपयोगकर्ता गाइड(बाल देखभाल संस्थान)</td>
                <td><a href="resources/hindi pdf/CPIS_CCI_USER_MANUAL_Hindi_final_r3.pdfcts_Rules/Bihar JJ Rules 2017 Part 1.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>उपयोगकर्ता गाइड(बतल कल्यतण समितर्)</td>
                <td><a href="resources/hindi pdf/CPIS_CWC_USER_MANUAL_r3......pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>उपयोगकर्ता गाइड(जिलत बतल संरक्षण इकतई)</td>
                <td><a href="resources/hindi pdf/CPIS_DCPU_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>उपयोगकर्ता गाइड(निदेशक)</td>
                <td><a href="resources/hindi pdf/CPIS_Director_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>उपयोगकर्ता गाइड(जिलत परिवीक्षतधीन अधधकतिी)</td>
                <td><a href="resources/hindi pdf/CPIS_DPO_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>उपयोगकर्ता गाइड(सहतयक मुख्य परिवीक्षतधीन अधधकतिी)</td>
                <td><a href="resources/hindi pdf/CPIS_Dy. CPO_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
			  <tr>
                <td>उपयोगकर्ता गाइड(ककशोर न्यतय बोडा)</td>
                <td><a href="resources/hindi pdf/CPIS_JJB_USER_MANUAL_r3.pdfnt Labour (Prohibition and Regulation) Act, 1986.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>उपयोगकर्ता गाइड(प्रधतन सचिव)</td>
                <td><a href="resources/hindi pdf/CPIS_Principal Secretary_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>उपयोगकर्ता गाइड(रतज्य बतल संरक्षण संस्थत)</td>
                <td><a href="resources/hindi pdf/CPIS_SCPS_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>
              <tr>
                <td>उपयोगकर्ता गाइड(एसजेपीयू )</td>
                <td><a href="resources/hindi pdf/CPIS_SJPU_USER_MANUAL_r3.pdf" download=""><i class="fa fa-2x fa-file-pdf-o fa-icon"></i></a></td>
              </tr>

           </tbody>
          </table>
              
              
              
              
      
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
