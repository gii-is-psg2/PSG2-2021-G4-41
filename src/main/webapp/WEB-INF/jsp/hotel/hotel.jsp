<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="hotel">
     <h2>Mascotas, Visitas y Reservas de habitaciones</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <br>
                        <dt>Nombre</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Fecha de nacimiento</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Tipo</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                        <dt>Acciones</dt>
                        <dd>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a class="glyphicon glyphicon-pencil"  href="${fn:escapeXml(petUrl)}"></a>

                                 <spring:url value="/owners/{ownerId}/pets/{petId}/delete" var="deletePetUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a class="glyphicon glyphicon-trash" href="${fn:escapeXml(deletePetUrl)}"></a>
                        </dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Fecha de visita</th>
                            <th>Descripción</th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                                <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/{visitId}/delete" var="deleteVisitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                    <spring:param name="visitId" value="${visit.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(deleteVisitUrl)}">Eliminar Visita</a>
                            </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Añadir Visita</a>
                            </td>
                        </tr>
                        
                    </table>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                    <thead>
                        <tr>
                            <th>Check In</th>
                            <th>Check Out</th>
                            <th></th>
                        </tr>
                        </thead>
                        <c:forEach var="roomBooking" items="${pet.roomBookings}">
                            <tr>
                                <td><petclinic:localDate date="${roomBooking.checkIn}" pattern="yyyy-MM-dd"/></td>
                                <td><petclinic:localDate date="${roomBooking.checkOut}" pattern="yyyy-MM-dd"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                                <td>
                                    <spring:url value="/owners/{ownerId}/pets/{petId}/roomBookings/new" var="bookingUrl">
                                        <spring:param name="ownerId" value="${owner.id}"/>
                                        <spring:param name="petId" value="${pet.id}"/>
                                    </spring:url>
                                    <a href="${fn:escapeXml(bookingUrl)}">Reservar una habitación</a>
                                </td>
                            </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>
    </div>
</petclinic:layout>