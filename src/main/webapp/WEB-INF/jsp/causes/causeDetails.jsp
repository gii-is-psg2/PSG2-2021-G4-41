<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Detalles de la causa</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Organización</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
        <tr>
            <th>Objetivo</th>
            <td><c:out value="${cause.target}"/></td>
        </tr>
        <tr>
        	<th>Donado hasta ahora</th>
        	<td><c:out value="${donated}"/></td>
        </tr>
    </table>
    
    <h3>Donaciones para esta causa</h3>
    
    
    <c:forEach var="donation" items="${cause.donations}">
    	<table class = "table table-striped">
    		<h4><c:out value="Donation made by ${donation.user.username}"/></h4>
    			<tr>
    				<th>Fecha de la donación</th>
       	 			<td><petclinic:localDate date="${donation.date}" pattern="yyyy-MM-dd"/></td>
       	 		</tr>
       	 		<tr>
       	 			<th>Cantidad donada</th>
                	<td><c:out value="${donation.amount}"/></td>
               		<!-- <td></td> -->
            	</tr>
    	
    	
    
    	</table>
    </c:forEach>

</petclinic:layout>
