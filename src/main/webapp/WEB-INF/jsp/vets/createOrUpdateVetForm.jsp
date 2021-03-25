<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
    	<c:if test="${vet['new']}"></c:if> New Vet
    </h2>
    <form:form modelAttribute="vet" class="form-horizontal" id="add-vet-form">
        <div class="form-group has-feedback">
			<petclinic:inputField label="Name" name="firstName"/>
			<petclinic:inputField label="LastName" name="lastName"/>
			<label for="specialty">Specialty:</label> <select
				name="specialties" id="specialties">
				<c:forEach items="${specialties}" var="specialties">
					<option value="${specialties}"><c:out
							value="${specialties}" /></option>
				</c:forEach>
				</select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vet['new']}">
                    <button class="btn btn-default" type="submit">Add</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Add</button>
					</c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>