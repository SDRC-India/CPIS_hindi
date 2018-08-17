<div ng-cloak id="constitutionVCPCBody" ng-controller="constitutionOfVCPCController">
	<input type="hidden" id="modelValue" value="${selectedChild }">
	<div class="modal fade" id="confirmationModalForConstitutionVCPC" tabindex="-1" role="dialog" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	          <h4 class="modal-title" id="myModalLabel">Please click on the submit button to save the details.</h4>
	      </div>
	      <div class="modal-body">
	          <p style="text-align:center">
	          <button id="button3id" name="button3id" class="btn btn-info bigbutton" type="submit" ng-click="saveVCPCConstitution()" class="close" data-dismiss="modal" aria-hidden="true">SUBMIT</button>
	          <button id="button4id" name="button4id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true">BACK</button>
	          </p>                     
	      </div>    
	    </div>
	  </div>
	</div>
	<div class="modal fade" id="constitutionVCPCModal" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-body">
	      	<p style="text-align:center">
	          	The form has been saved successfully.<br>
<!-- 	          <button id="button5id" name="button6id" class="btn btn-info bigbutton" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="onstitutionCWCSubmitData()">Print</button> -->
	          <button id="button5id" name="button5id" class="btn btn-info bigbutton2" type="submit" class="close" data-dismiss="modal" aria-hidden="true" ng-click="reLoad()">Ok</button>
	        </p>                     
	      </div>    
	    </div>
	  </div>
	</div>

	<div class="box-border box-border-padding">
		<div class="childlist-heading1 borderPersonal">
			Constitution of VCPC	<br>
			
		</div>

		<form class="form-horizontal basicchildform" name="constitutionVCPC" id="constitutionVCPC">
			<fieldset>


				<div class="form-group box-border-padding constitutiontop">
								<label class="col-md-4 col-sm-12 col-xs-12 control-label" for="textinput">1. Constitution date of the VCPC<span class="mandatory_star">&#42;</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-6">
									<input type="text" id="vcpc_datepicker"
										readonly class="form-control" ng-disabled="viewPage">
									<div id="constitutiondateoftheVCPCError" class="error-style"></div>
								</div>
								<i class="fa fa-calendar fa-5x" style="font-size:27px !important;margin-top: 3px;
								color: #396c5d;margin-left: -7px;" aria-hidden="true"></i>
							</div>


				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">2. Name of the Head Man of the Village</label>
					<div class="col-md-7">
						<input id="nameheadMan" name="nameheadMan" placeholder="Enter name of the head man of the village"
						ng-model="vcpcConstitution.vlgHeadManName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="constitutionVCPCData" class="form-control" ng-model="vcpcConstitution.vlgHeadManSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of Head Man of the Village </label>
								<div class="col-md-7">
									<input only-ten-digits id="vlgHeadMancontactNumber" name="contactNumbberofHeadman" 
										placeholder="Enter contact number of Head Man of the Village"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.vlgHeadManContact"
							  			ng-keyUp="validateName(vcpcConstitution.vlgHeadManContact,'vlgHeadManPhoneNoError')"
										class="form-control input-md">
										<div id="vlgHeadManPhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Head Man of the Village</label>
					<div class="col-md-7">
						<input id="emailofHeadman" name="emailofHeadman" ng-model="vcpcConstitution.vlgHeadManEmail"
							placeholder="Enter email id of the Head Man of the Village" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.vlgHeadManEmail,'emailheadmanError')">
							<div id=emailheadmanError class="error-style"></div>
					</div>
				</div>
				


				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">3. Name of the Auxilliary Nurse Midwives (ANM)</label>
					<div class="col-md-7">
						<input id="anm" name="anm" placeholder="Enter name of the auxilliary nurse midwives (ANM)"
						ng-model="vcpcConstitution.anmName"	class="form-control input-md" type="text" >
					</div>
				</div>

							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Contact
									No. of the Auxilliary Nurse Midwives (ANM) </label>
								<div class="col-md-7">
									<input only-ten-digits id="anmContactNumber" name="contactNumbberANM" 
										placeholder="Enter contact number of auxilliary nurse midwives (ANM)"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.anmContact"
							  			ng-keyUp="validateName(vcpcConstitution.anmContact,'anmPhoneNoError')"
										class="form-control input-md">
										<div id="anmPhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(ii) Email id of the Auxilliary Nurse Midwives (ANM)</label>
					<div class="col-md-7">
						<input id="emailpersonANM" name="emailpersonANM" ng-model="vcpcConstitution.anmEmail"
							placeholder="Enter email id of the auxilliary Nurse Midwives (ANM)" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.anmEmail,'emailanmError')">
							<div id=emailanmError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">4. Name of the Anganwadi Worker</label>
					<div class="col-md-7">
						<input id="member4" name="member4" placeholder="Enter name of the anganwadi worker"
						ng-model="vcpcConstitution.awName"	class="form-control input-md" type="text" >
					</div>
				</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Contact
									No. of the Anganwadi Worker </label>
								<div class="col-md-7">
									<input only-ten-digits id="awContactNumber" name="contactNumbberAWW" 
										placeholder="Enter contact number of anganwadi worker"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.awContact"
							  			ng-keyUp="validateName(vcpcConstitution.awContact,'awPhoneNoError')"
										class="form-control input-md">
										<div id="awPhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(ii) Email id of the Anganwadi Worker</label>
					<div class="col-md-7">
						<input id="emailpersonAganWadiWorker" name="emailpersonAganWadiWorker" ng-model="vcpcConstitution.awEmail"
							placeholder="Enter email id of anganwadi worker" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.awEmail,'emailawError')">
							<div id=emailawError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">5. Name of the School Teacher</label>
					<div class="col-md-7">
						<input id="schoolTeacher" name="schoolTeacher" placeholder="Enter name of the school teacher"
						ng-model="vcpcConstitution.schoolTeacherName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="schoolteacherData" class="form-control" ng-model="vcpcConstitution.schoolTeacherSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the School Teacher </label>
								<div class="col-md-7">
									<input only-ten-digits id="stContactNumber" name="contactNumbberschoolTeacher" 
										placeholder="Enter contact number of school teacher"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.schoolTeacherContact"
							  			ng-keyUp="validateName(vcpcConstitution.schoolTeacherContact,'stPhoneNoError')"
										class="form-control input-md">
										<div id="stPhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the School Teacher</label>
					<div class="col-md-7">
						<input id="emailschoolTeacher" name="emailschoolTeacher" ng-model="vcpcConstitution.schoolTeacherEmail"
							placeholder="Enter email id of the school teacher" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.schoolTeacherEmail,'emailstError')">
							<div id=emailstError class="error-style"></div>
					</div>
				</div>
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">6. Name of the Member from DCPU</label>
					<div class="col-md-7">
						<input id="memberfromDCPU" name="memberfromDCPU" placeholder="Enter name of the member from DCPU"
						ng-model="vcpcConstitution.dcpuMemberName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="member6Data" class="form-control"  ng-model="vcpcConstitution.dcpuMemberSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the Member from DCPU </label>
								<div class="col-md-7">
									<input only-ten-digits id="dcpuContactNumber" name="contactNumbberFromDCPU" 
										placeholder="Enter contact number of member from DCPU"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.dcpuMemberContact"
							  			ng-keyUp="validateName(vcpcConstitution.dcpuMemberContact,'dcpuPhoneNoError')"
										class="form-control input-md">
										<div id="dcpuPhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Member from DCPU</label>
					<div class="col-md-7">
						<input id="emailperson6" name="emailperson6" ng-model="vcpcConstitution.dcpuMemberEmail"
							placeholder="Enter email id of the member from DCPU" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.dcpuMemberEmail,'emailvcpcmemberError')">
							<div id=emailvcpcmemberError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">7. Name of Child Representative 1</label>
					<div class="col-md-7">
						<input id="representative1" name="representative1" placeholder="Enter name of the child representitive 1"
						ng-model="vcpcConstitution.crOneName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="representativesex" class="form-control" ng-model="vcpcConstitution.crOneSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the Child Representative 1 </label>
								<div class="col-md-7">
									<input only-ten-digits id="cr1ContactNumber" name="contactNumbbermember1" 
										placeholder="Enter contact number of child representative 1"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.crOneContact"
							  			ng-keyUp="validateName(vcpcConstitution.crOneContact,'cr1PhoneNoError')"
										class="form-control input-md">
										<div id="cr1PhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Child Representitive 1</label>
					<div class="col-md-7">
						<input id="representativeemail" name="representativeemail" ng-model="vcpcConstitution.crOneEmail"
							placeholder="Enter email id of the child representitive 1" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.crOneEmail,'emailcrOneError')">
							<div id=emailcrOneError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">8. Name of child Representative 2</label>
					<div class="col-md-7">
						<input id="representative2" name="represetantive2" placeholder="Enter name of the child Representative 2"
						ng-model="vcpcConstitution.crTwoName" class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="representative2sex" class="form-control" ng-model="vcpcConstitution.crTwoSex.id" 
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the child Representative 2 </label>
								<div class="col-md-7">
									<input only-ten-digits id="cr2ContactNumber" name="contactNumbberRepresentative2" 
										placeholder="Enter contact number of child Representative 2"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.crTwoContact"
							  			ng-keyUp="validateName(vcpcConstitution.crTwoContact,'cr2PhoneNoError')"
										class="form-control input-md">
										<div id="cr2PhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the child<br> Representative 2</label>
					<div class="col-md-7">
						<input id="Representative2email1" name="Representative2email1" ng-model="vcpcConstitution.crTwoEmail"
							placeholder="Enter email id of the child Representative 2" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.crTwoEmail,'emailcrTwoError')">
							<div id=emailcrTwoError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">9. Name of the Village member 1</label>
					<div class="col-md-7">
						<input id="Villagemember1" name="Villagemember1" placeholder="Enter name of the Village member 1"
						ng-model="vcpcConstitution.vlgMemberOneName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="Villagemember1sex" class="form-control"  ng-model="vcpcConstitution.vlgMemberOneSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the Village member 1</label>
								<div class="col-md-7">
									<input only-ten-digits id="vm1ContactNumber" name="contactNumbberVillagemember1" 
										placeholder="Enter contact number of village member 1"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.vlgMemberOneContact"
							  			ng-keyUp="validateName(vcpcConstitution.vlgMemberOneContact,'vm1PhoneNoError')"
										class="form-control input-md">
										<div id="vm1PhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Village member 1</label>
					<div class="col-md-7">
						<input id="Villagemember1email1" name="Villagemember1email1" ng-model="vcpcConstitution.vlgMemberOneEmail"
							placeholder="Enter email id of the Village member 1" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.vlgMemberOneEmail,'emailvmOneError')">
							<div id=emailvmOneError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">10. Name of the Village member 2</label>
					<div class="col-md-7">
						<input id="Villagemember2" name="Villagemember2" placeholder="Enter name of the village member 2"
						ng-model="vcpcConstitution.vlgMemberTwoName" class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="Villagemember2sex1" class="form-control"  ng-model="vcpcConstitution.vlgMemberTwoSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the <br>Village member 2</label>
								<div class="col-md-7">
									<input only-ten-digits id="vm2contactNumber" name="contactNumberVillagemember2" 
										placeholder="Enter contact number of village member 2"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.vlgMemberTwoContact"
							  			ng-keyUp="validateName(vcpcConstitution.vlgMemberTwoContact,'vm2PhoneNoError')"
										class="form-control input-md">
										<div id="vm2PhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Village member 2</label>
					<div class="col-md-7">
						<input id="Villagemember2email4" name="Villagemembe2email4" ng-model="vcpcConstitution.vlgMemberTwoEmail"
							placeholder="Enter email id of the village member 2" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.vlgMemberTwoEmail,'emailvmtwoError')">
							<div id=emailvmtwoError class="error-style"></div>
					</div>
				</div>
				
				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">11. Name of the Village member 3</label>
					<div class="col-md-7">
						<input id="Villagemember2" name="Villagemember2" placeholder="Enter name of the village member 3"
						ng-model="vcpcConstitution.vlgMemberThreeName"	class="form-control input-md" type="text" >
					</div>
				</div>

				<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(i) Sex</label>
								<div class="col-md-7">
								   <select id="Villagemember3sex1" class="form-control" ng-model="vcpcConstitution.vlgMemberThreeSex.id"
								  	oninvalid="this.setCustomValidity('Select ')" oninput="setCustomValidity('')" >
								  	<option value="" disabled selected>Select</option>
								   	<option ng-repeat="item in sex" ng-value="{{item.id}}">{{item.name}}</option>
								   </select>

								</div>
							</div>
							
							<div class="form-group box-border-padding">
								<label class="col-md-4 control-label" for="textinput">(ii) Contact
									No. of the Village member 3</label>
								<div class="col-md-7">
									<input only-ten-digits id="vm3contactNumber" name="contactNumberVillagemember3" 
										placeholder="Enter contact number of village member 3"
										oninvalid="this.setCustomValidity('Please enter contact no. of the person')" 
										oninput="setCustomValidity('')"
							  			ng-pattern="/^[0-9]{1}[0-9]{9}$/"
							  			ng-model="vcpcConstitution.vlgMemberThreeContact"
							  			ng-keyUp="validateName(vcpcConstitution.vlgMemberThreeContact,'vm3PhoneNoError')"
										class="form-control input-md">
										<div id="vm3PhoneNoError" class="error-style"></div>
								</div>
							</div>

				<div class="form-group box-border-padding">
					<label class="col-md-4 control-label" for="textinput">(iii) Email id of the Village member 3</label>
					<div class="col-md-7">
						<input id="Villagemember3email4" name="Villagemembe3email4" ng-model="vcpcConstitution.vlgMemberThreeEmail"
							placeholder="Enter email id of the village member 3" class="form-control input-md"
							type="text" ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
							ng-keyUp="validateName(vcpcConstitution.vlgMemberThreeEmail,'emailvmthreeError')">
							<div id=emailvmthreeError class="error-style"></div>
					</div>
				</div>
				



				<div style="text-align: center" >
					<button  id="buttonid" name="button1id" class="btn btn-info" ng-click ="constitutionVCPC.$invalid ? '' : validateVCPCForm()"
						type="submit">Submit</button>
				</div>
			</fieldset>
		</form>
		<div class="modal fade" id="errorImageModal" tabindex="-1"	role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<p style="text-align: center">
							Uploaded file is not in correct format.<br>
							<button id="button5id" name="button5id" class="btn btn-info"
								type="submit" class="close" data-dismiss="modal"
								aria-hidden="true">Ok</button>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
			$("#datepicker1").datepicker({
				dateFormat : "yy-mm-dd",
				maxDate : '+0d'
			});
// 			$("#buttonid").click(function(){
// 				$('#confirmationModalForCaseSummary').modal('show');
// 			})
			
		});
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/zooming/1.1.1/zooming.min.js"></script>	
	<spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js"
		var="jQueryUiCore" />
	<script src="${jQueryUiCore}"></script>
	<script type="text/javascript" src="resources/js/download.js"></script>
