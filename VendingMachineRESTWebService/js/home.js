//home.js
$(document).ready(function () {
    loadInventory();
//    $(".snack").click( function () {
////                console.log("hello");
////
//////                $("#selectedItem").val(snack.id);
//            });
});

var dollarClickCount = 0;
var quarterClickCount = 0;
var dimeClickCount = 0;
var nickelClickCount = 0;

function loadInventory() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/items",
        success: function (snackArray) {

            // Get a reference to the 'allContacts' div.
            var snacksDiv = $("#snacks");
            snacksDiv.empty();
//            var selectedItem = $("#selectedItem");
            // Go through each contact in the result and append its info
            // to the contactsDiv.
            $.each(snackArray, function (index, snack) {
                var snackInfo = "<button onclick='selectSnack(" + snack.id + ")' id='" + snack.id + "' class= 'col-sm-3 snack'>";

                snackInfo += "<p class= 'snackSlot'>" + snack.id + "</p>" + "<br />";
                snackInfo += "<p class= 'snackName'>" + snack.name + "</p>" + "<br />";
                snackInfo += "<p class= 'snackPrice'>" + snack.price + "</p>" + "<br />";
                snackInfo += "<p class= 'snackQuantity'>" + "Quantity Left: " + snack.quantity + "</p>" + "<br />";
                snackInfo += "</button>";


                snacksDiv.append(snackInfo);
            });

//            $(".snack").on("click", function () {
//                console.log("hello");
//
//                $("#selectedItem").val(snack.id);
//            });
        },
        error: function () {
            alert("FAILURE!");
        }
    });
}

function purchaseSnack(amount, id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/money/" + amount + "/item/" + id,
        success: function (change) {

            var changeString = "";
            if (change.quarters > 0) {
                changeString += change.quarters + " Quarters ";
            }
            if (change.dimes > 0) {
                changeString += change.dimes + " Dimes ";
            }
            if (change.nickels > 0) {
                changeString += change.nickels + " Nickels ";
            }
            if (change.pennies > 0) {
                changeString += change.pennies + " Pennies ";
            }

            $("#changeReturned").val(changeString);
            $("#messageDisplay").val("Thank You!!!");
        },
        error: function (res) {
            $("#messageDisplay").val(res.responseJSON.message);
        }
    });
}
function selectSnack(snackId) {
    $("#selectedItem").val(snackId);
}

$("#makePurchase").on("click", function () {
    var currentAmount = Number($("#amountInput").text());
    var selectedItem = $("#selectedItem").val();
    var snackPrice = $("#" + selectedItem).find(".snackPrice").text();
    console.log(Number(selectedItem));
    if (Number(selectedItem)) {
        if (currentAmount < snackPrice) {
            $("#messageDisplay").val("Please deposit: $" + (snackPrice - currentAmount));
        } else {
            purchaseSnack(currentAmount, selectedItem);
            loadInventory();
        }
    } else {
        $("#messageDisplay").val("No Item Selected. Select Item.");
    }



});



$("#addDollar").on("click", function () {
    var currentAmount = Number($("#amountInput").text());

    dollarClickCount += 1;
    currentAmount += 1;
    $("#amountInput").html(currentAmount.toFixed(2));
});

$("#addQuarter").on("click", function () {
    var currentAmount = Number($("#amountInput").text());

    quarterClickCount += 1;
    currentAmount += .25;
    $("#amountInput").html(currentAmount.toFixed(2));
});

$("#addDime").on("click", function () {
    var currentAmount = Number($("#amountInput").text());

    dimeClickCount += 1;

    currentAmount += .10;
    $("#amountInput").html(currentAmount.toFixed(2));
});

$("#addNickel").on("click", function () {
    var currentAmount = Number($("#amountInput").text());

    nickelClickCount += 1;
    currentAmount += .05;
    $("#amountInput").html(currentAmount.toFixed(2));
});

$("#returnChange").on("click", function () {
    // Clear Inputs
    $("#messageDisplay").val("");
    $("#selectedItem").val("");
   // $("#changeReturned").val("");
    //Update Item List
    loadInventory();
    // Prepare for next purchase
    $("#amountInput").text("0.00");
    //give change back
    var changeBack = "";
    console.log(Number(quarterClickCount));
    if (dollarClickCount > 0) {
        changeBack += dollarClickCount.toString() + " Dollars ";
    }
    if (quarterClickCount > 0) {
        changeBack += quarterClickCount.toString() + " Quarters ";
    }
    if (dimeClickCount > 0) {
        changeBack += dimeClickCount.toString() + " Dimes ";
    }
    if (nickelClickCount > 0) {
        changeBack += nickelClickCount.toString() + " Nickels ";
    }
//    changeBack = dollarClickCount.toString() + " Dollars " + quarterClickCount.toString()
//            + " Quarters " + dimeClickCount.toString() + " Dimes " + nickelClickCount.toString() + " Nickels ";
    $("#changeReturned").val(changeBack);
    

});
