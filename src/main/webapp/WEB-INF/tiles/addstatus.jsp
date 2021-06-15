<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- tag libraries imports -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!-- this is actually already in our libraries, but we use it to specify -->

<div class="row">
	<div class="col-md-8 col-md-offset-2">
	
	<!-- for debugging purposes -->
	<%-- Request statusUpdate attribute: <%= request.getAttribute("statusUpdate") %> <br/> <!-- we can see the difference when we just go to this page (get), and when we add something to the form (post) -->
	JSP object: <%= this %> <br/>
	JSP class: <%= this.getClass() %> <br/> --%>
	<!--  -->
	
	<!-- we use c:out in case statusUpdate has html in it -->
	<!-- to insert statusUpdate, we need to add the taglib prefix='c' above -->
		<%-- <c:out value="${statusUpdate}" /> --%> <!-- what we put in the model (PageController.java, @RequestMapping("/addstatus")) -->
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Add a Status Update</div>
			</div>
			<!-- <div class="panel-body"> -->
				<form:form modelAttribute="statusUpdate">
					<div class="errors">
						<form:errors path="text" /> <!-- the errors will show here (for example: "size must be between 5 and 255" ) -->
					</div>
					<div class="form-group">
					<!-- the path="text" is the attribute where what is put here should be stored (StatusUpdate has id, text and added)-->
						<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea> 
					</div>
					<input type="submit" name="submit" value="Add Status" />
				</form:form>
			<!-- </div> -->
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Status update added on <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:ss" value="${latestStatusUpdate.added}" /></div> <!-- we can find latestStatusUpdate in PageController.java --> <!-- we cannot do .getAdded() --><!-- we see the date of the latest status-->
			</div>
			<div class="panel-body">
				<c:out value="${latestStatusUpdate.text}" /> <!-- we can now see below what we last wrote on the form (we retrieve it from the database -->
			</div>
		</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.0.20/tinymce.min.js"></script>
<script>
	tinymce.init({
		selector: 'textarea',
		plugins: 'link'
	});
</script>