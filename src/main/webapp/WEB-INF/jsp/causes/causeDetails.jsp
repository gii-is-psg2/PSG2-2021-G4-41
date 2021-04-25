<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">

    <h2>Cause details</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${cause.name}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>Organization</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
        <tr>
            <th>Target</th>
            <td><c:out value="${cause.target}"/></td>
        </tr>
        <tr>
        	<th>Donated until now</th>
        	<td><c:out value="${donated}"/></td>
        </tr>
    </table>
    
    <h3>Donations for this cause</h3>
    
    <table class = "table table-striped">
    	<c:forEach var="donation" items="${cause.donations}">
    	<h4><c:out value="Donation made by ${donation.user.username}"/></h4>
    		<tr>
    			<th>Donation date</th>
       	 		<td><petclinic:localDate date="${donation.date}" pattern="yyyy-MM-dd"/></td>
       	 	</tr>
       	 	<tr>
       	 		<th>Amount donated</th>
                <td><c:out value="${donation.amount}"/></td>
               	<!-- <td></td> -->
            </tr>
    	
    	</c:forEach>
    
    </table>

</petclinic:layout>
