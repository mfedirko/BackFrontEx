// The root URL for the RESTful services
var rootURL = "http://localhost:8080/transaction";

var user=$('#user').text()
var currentWine;
var currentElement;
// Retrieve wine list when application starts 
//findAll();
$('#txnList').hide();

//---------------- Register listeners------------------


// Trigger search when pressing 'Return' on search key input field
//$('#searchKey').keypress(function(e){
//	if(e.which == 13) {
//		search($('#searchKey').val());
//		e.preventDefault();
//		return false;
//    }
//});


$('#btnSubmit').click(function() {
	transact();
	findAll();
	return false;
});

//$('#wineList a').live('click', function() {
//	findById($(this).data('identity'));
//});

//// Replace broken images with generic wine bottle
//$("img").error(function(){
//  $(this).attr("src", "pics/generic.jpg");
//
//});
//--------------------------------------------------


//
//function search(searchKey) {
//	if (searchKey == '') 
//		findAll();
//	else
//		findFreeForm(searchKey);
//}
//
//function newWine() {
//	$('#btnDelete').hide();
//	currentWine = {};
//	renderDetails(currentWine); // Display empty form
//}



//function findFreeForm(searchKey) {
//	console.log('findByName: ' + searchKey);
//	$.ajax({
//		type: 'GET',
//		url: rootURL + '/freeform/' + $('#criteria').val() +'/'+ searchKey,
//		dataType: "json",
//		success: renderList 
//	});
//}
//
function findAll() {
	console.log('findAll: ');
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + user,
		dataType: "json",
		success: function(data){
			
			console.log('looked up transactions for current user');
			
			renderList(data);
		}
	});
}

function transact() {
	console.log('new transaction');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Transaction created successfully');
		//	$('#btnDelete').show();
			//$('#acct_nb').val(data.accounts[0].acct_nb);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Error creating transaction: ' + textStatus + ' '+errorThrown);
		}
	});
}


function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list=[];
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#txnList td').remove();
	$('#txnList tr').remove();
	$('#txnList').show();
	$.each(list, function(index, txn) {
		$('#txnList').append('<tr><td>'+txn.amount+'</td><td>'+txn.date+'</td><td>'+txn.fromAccountId+'</td><td>'+txn.toAccountId+'</td></tr>');
	});
}



// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	//var wineId = $('#wineId').val();
	return JSON.stringify({
		"amount": $('#amount').val(), 
		"date": $('#date').val(),
		"toAccountId": $('#toAccountId').val(),
		"fromAccountId": $('#fromAccountId').val()
		});
}