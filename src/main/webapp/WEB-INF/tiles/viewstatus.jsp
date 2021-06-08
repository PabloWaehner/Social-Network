<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>

<%-- Number of page is: 	${param.p} --%> <!-- if the url is viewstatus?p=11, I will see "Number of page is: 11 -->

<c:url var="url" value="/viewstatus" /> <!-- c:url adds the context root/path automatically (we are hard-coding it because we use this url all the time) -->
 
<div class="row">
	<div class="col-md-8 col-md-offset-2">
		<jwp:pagination url="${url}" page="${page}" size="10"/>
		<c:forEach var="statusUpdate" items="${page.content}"> <!-- in PageController we do modelAndView.getModel().put("page", page), that's why it's "page" -->
			 <div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						Status update added on <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s" value="${statusUpdate.added}" />
					</div>
				</div>
				<div class="panel-body">
					<c:out value="${statusUpdate.text}" />
				</div>
			</div>
		</c:forEach>
	</div>
</div>