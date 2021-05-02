<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="causes">
    
    <jsp:body>
    
    	
    	
        <h2>Cause</h2>

        

        <form:form action="/causes/save" modelAttribute="cause" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Name" name="name"/>
               	<petclinic:inputField label="Description" name="description"/>
               	<petclinic:inputField label="Budget target" name="target"/>
               	<petclinic:inputField label="Organization" name="organization"/>
               	
           </div>  	

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${cause.id}"/>
                    <button class="btn btn-default" type="submit">Create cause</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>