<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="createAdoptionApplication">    
    <jsp:body>

	<h2><c:if test="${applications['new']}"></c:if>New Adoption Application</h2>
	
    <form:form modelAttribute="newApplication" class="form-horizontal">
        <div class="form-group has-feedback">
        	<petclinic:inputField label="Description" name="description"/>   
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<input type="hidden" name="requestId" value="${applications.request.id}"/>
            	<input type="hidden" name="ownerId" value="${applications.owner.id}"/>
            	<button class="btn btn-default" type="submit">Do an Adoption Request</button>
            </div>
        </div>
    </form:form>  
    
    </jsp:body>
</petclinic:layout>