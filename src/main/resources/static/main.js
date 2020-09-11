$(document).ready(function() {
    $.ajax({
       // url: "https://api.nbp.pl/api/cenyzlota/last?format=json"
    }).then(function() {
        $('#jstext').append("Hello from JS");
        // $('.jslink').append("google.com");
        // $('.greeting-content').append(data[0].cena);
    });
});