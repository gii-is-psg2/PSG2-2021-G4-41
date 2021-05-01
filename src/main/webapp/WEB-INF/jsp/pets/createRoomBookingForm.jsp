<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#checkIn").datepicker({dateFormat: 'yy/mm/dd'});
                $("#checkOut").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${roomBooking['new']}">New </c:if>Room Bookings</h2>

        <b>Pet</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Type</th>
                <th>Owner</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${roomBooking.pet.name}"/></td>xº
                <td><petclinic:localDate date="${roomBooking.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${roomBooking.pet.type.name}"/></td>
                <td><c:out value="${roomBooking.pet.owner.firstName} ${roomBooking.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="roomBooking" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Check In Date" name="checkIn"/>
                <petclinic:inputField label="Check Out Date" name="checkOut"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${roomBooking.pet.id}"/>
                    <button class="btn btn-default" type="submit">Book a room</button>
                </div>
            </div>
        </form:form>

        <br/>
        <b>Previous Room Bookings</b>
        <table class="table table-striped">
            <tr>
                <th>Check In</th>
                <th>Check Out</th>
            </tr>
            <c:forEach var="roomBooking" items="${roomBooking.pet.roomBookings}">
                <c:if test="${!roomBooking['new']}">
                    <tr>
                        <td><petclinic:localDate date="${roomBooking.checkIn}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${roomBooking.checkOut}" pattern="yyyy/MM/dd"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
