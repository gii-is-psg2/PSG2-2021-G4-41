<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="donations">
    
    <jsp:body>
    
    	 <form:form modelAttribute="donation" class="form-horizontal">
    	
        <h2>Donación para <c:out value="${donation.cause.name}"/> causa</h2>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Amount to donate" name="amount"/>
            </div>  	

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="causeId" value="${donation.cause.id}"/>
                    <button class="btn btn-default" type="submit">Submit donation</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>