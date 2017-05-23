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

        //CHART
        var chartData = new google.visualization.DataTable();
        chartData.addColumn('number', 'Day');
        chartData.addColumn('number', 'Z1');
        chartData.addColumn('number', 'Z2');
        chartData.addColumn('number', 'Z3');

        chartData.addRows([
            [1,  37.8, 80.8, 41.8],
            [3,  25.4,   57, 25.7],
            [4,  11.7, 18.8, 10.5],
            [6,   8.8, 13.6,  7.7],
            [7,   7.6, 12.3,  9.6],
            [8,  12.3, 29.2, 10.6],
            [10, 12.8, 30.9, 11.6],
            [11,  5.3,  7.9,  4.7],
            [13,  4.8,  6.3,  3.6],
            [14,  4.2,  6.2,  3.4]
        ]);

        var options = {
        chart: {
            title: 'Zarządzanie danymi',
            subtitle: 'Wykres'
        },
            width: 900,
            height: 500
        };

        var chart = new google.charts.Line(document.getElementById('chart_container'));
        chart.draw(chartData, google.charts.Line.convertOptions(options));
    });
});