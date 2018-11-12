<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="css/vendingMachineStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <h1>Vending Machine</h1>
            <hr />

            <div class="row">
                <!-- Vending Snack Selection Section -->
                <div class="col-md-9" id="snacks">
                    <form role="form" method="POST" action="itemSelect">
                        <c:forEach var="currentSnack" items="${snackList}">
                            <button type="submit" name="itemSelected" value="${currentSnack.slotId}" class="col-sm-3 snack">
                                <p class="snackSlot">
                                    <c:out value="${currentSnack.slotId}"/>
                                </p>
                                <p class="snackName">
                                    <c:out value="${currentSnack.snackName}"/> 
                                </p>
                                <p class="snackPrice"> $
                                    <c:out value="${currentSnack.snackPrice}"/>
                                </p>
                                <p class="snackQuantity"> Quantity Left:
                                    <c:out value="${currentSnack.snackAmount}"/>
                                </p>
                            </button>
                        </c:forEach>
                    </form>

                </div>

                <!-- Three Form Sections: AmountInput, PurchaseOption, ChangeReturn -->
                <div class="col-md-3">
                    <div id="total-amount-in" class="col-lg-12">
                        <form role="form" method="POST" action="addMoney">
                            <div class="col-lg-12 text-center">
                                <h3>Total $ In</h3>
                                <input class="form-control" type="number" name="currentAmount" id="amountInput" value="${interaction.amountInput}" readonly/>       
                            </div>
                            <div class="col-lg-12 text-center">
                                <button type="submit" name="value" value="1.00" id="addDollar" class="btn btn-outline-dark col-lg-6">Add Dollar</button>
                                <button type="submit" name="value" value="0.25" id="addQuarter" class="btn btn-outline-dark col-lg-6">Add Quarter</button>
                            </div>
                            <div class="col-lg-12 text-center" id="bottomButton">
                                <button type="submit" name="value" value="0.10" id="addDime" class="btn btn-outline-dark col-lg-6">Add Dime</button>
                                <button type="submit" name="value" value="0.05" id="addNickel" class="btn btn-outline-dark col-lg-6">Add Nickel</button>
                            </div>
                        </form>
                    </div>
                    <div id="messages" class="col-lg-12 text-center">
                        <form role="form" method="POST" action="purchaseItem">
                            <h3>Messages</h3>
                            <input class="form-control" type="text" id="messageDisplay" value="${interaction.message}" readonly>
                            <label for="selectedItem">Item Selected:</label>
                            <input class="form-control" name="itemSelected" type="number" id="selectedItem" value="${interaction.selectedItem}">
                            <br />
                            <button type="submit" id="makePurchase" class="btn btn-outline-dark">Make Purchase</button>
                        </form>
                    </div>
                    <div id="change" class="col-lg-12 text-center">
                        <form role="form" method="POST" action="changeReturn">
                            <h3>Change</h3>
                            <input class="form-control" type="text" id="changeReturned" value="${interaction.change}" readonly>
                            <button type="submit" id="returnChange" class="btn btn-outline-dark">Change Return</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

