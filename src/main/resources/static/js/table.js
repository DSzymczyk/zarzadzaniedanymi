$(document).ready(function () {

    var tableData = [], chartData = [];

    $('#add_button').on('click', function(){
        $('#input_file').click();
    });

    google.charts.load('current', {'packages':['line', 'table']});
    google.charts.setOnLoadCallback( function () {
        //TABLE
        var tableData = new google.visualization.DataTable();
        tableData.addColumn('string', 'Nazwa');
        tableData.addColumn('boolean', 'Poprawny');
        tableData.addRows([
            ['step1.k', true],
            ['step2.k',  false],
            ['step3.k', true],
            ['step4.k',  true]
        ]);

        var table = new google.visualization.Table(document.getElementById('table_container'));
        table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});
    });
});