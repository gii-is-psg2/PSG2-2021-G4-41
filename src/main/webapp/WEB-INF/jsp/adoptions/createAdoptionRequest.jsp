<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="createAdoptionRequest">    
    <jsp:body>

	<h2><c:if test="${requests['new']}"></c:if>New Adoption Application</h2>
	
    <form:form modelAttribute="newRequest" class="form-horizontal">
        <div class="form-group has-feedback">
        	<label for="requestPets">Pets:</label> 
			<select multiple
				name="pet" id="pet">
				<c:forEach items="${pets}" var="pet">
					<option value="${pet.id}">
						<c:out value="${pet.name}"/>
					</option>
				</c:forEach>
				</select>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<input type="hidden" name="ownerId" value="${applications.owner.id}"/>
            	<button class="btn btn-default" type="submit">Do an Adoption Request</button>
            </div>
        </div>
    </form:form>  
    
    </jsp:body>
</petclinic:layout>