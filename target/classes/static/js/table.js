$(document).ready(function () {

    var tableData = [], chartData = [], file, table, scene;

    $('#add_button').on('click', function(){
        $('#input_file').click();
    });

    $('#delete_button').on('click', function(){
        if (typeof table.getSelection()[0] !== 'undefined') {
            var selected = table.getSelection()[0].row;
            var id = tableData.getValue(selected, 0);
            $.ajax({
                type: "GET",
                url: '/deleteFile?id='+id,
                success: function(){
                    reloadTable();
                }
            });
        }
    });



    $("input:file").change(function (){
        var input = $('#input_file')[0];
        if ( input.files && input.files[0] ) {
            file = input.files[0];
            var fr = new FileReader();
            fr.readAsText( file );
            fr.onload = function (oFREvent) {
                $.ajax({
                    type: "POST",
                    url: '/sendData',
                    data: {
                        filename: file.name,
                        data: oFREvent.target.result
                    },
                    success: function(){
                        reloadTable();
                    }
                });
            }
        }
    });

    google.charts.load('current', {'packages':['line', 'table']});
    google.charts.setOnLoadCallback( function () {
        //TABLE
        reloadTable();
    });
    var input = document.getElementById('fileinput');

    function reloadTable(){
        tableData = new google.visualization.DataTable();
        tableData.addColumn('number', 'Id');
        tableData.addColumn('string', 'Nazwa');
        tableData.addColumn('date', 'Data');
        tableData.addColumn('boolean', 'Poprawny');
        $.ajax({
            type: "GET",
            url: '/getLoadedFiles',
            success: function(data) {
                data.forEach( function(d) {
                tableData.addRow(
                        [d.id, d.filename, new Date(d.date), d.valid]
                    );
                });
                table = new google.visualization.Table(document.getElementById('table_container'));
                table.draw(tableData, {showRowNumber: true, width: '100%', height: '100%'});

                    google.visualization.events.addListener(table, 'select', function(){
                        if (typeof table.getSelection()[0] !== 'undefined') {
                            var selected = table.getSelection()[0].row;
                            var id = tableData.getValue(selected, 0);
                            $.ajax({
                                type: "GET",
                                url: '/getFileData?id='+id,
                                success: function(data){
                                    if (typeof scene !== 'undefined') {
                                        for( var i = scene.children.length - 1; i >= 0; i--) {
                                            obj = scene.children[i];
                                             scene.remove(obj);
                                        }
                                    }
                                    $('#mycanvas').empty();
                                    draw(data);
                                }
                            });
                        }
                    });
            }
        });
    }

    function draw(data){
        var camera;
        var pyramidMesh;
        var cubeMesh;
        var controls;
        initializeScene();
        animateScene();

        function initializeScene(){
            if(Detector.webgl){
             renderer = new THREE.WebGLRenderer({antialias:true});
            } else {
             renderer = new THREE.CanvasRenderer();
            }
            renderer.setClearColor(0xffffff, 1);

            canvasWidth = 950;
            canvasHeight = 900;
            renderer.setSize(canvasWidth, canvasHeight);
            document.getElementById("mycanvas").appendChild(renderer.domElement);
            scene = new THREE.Scene();
            camera = new THREE.PerspectiveCamera(50, canvasWidth / canvasHeight, 1, 100);
            camera.position.set(0, 0, 10);
            camera.lookAt(scene.position);
            scene.add(camera);
            controls = new THREE.OrbitControls( camera, renderer.domElement );



            var i;
            for(i=0; i<data.elementList.length; i++){
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[1]-1], data.nodesList[data.elementList[i].nodeList[2]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[2]-1], data.nodesList[data.elementList[i].nodeList[3]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[3]-1], data.nodesList[data.elementList[i].nodeList[7]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[4]-1], data.nodesList[data.elementList[i].nodeList[7]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[1]-1], data.nodesList[data.elementList[i].nodeList[5]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[0]-1], data.nodesList[data.elementList[i].nodeList[4]-1], data.nodesList[data.elementList[i].nodeList[5]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[1]-1], data.nodesList[data.elementList[i].nodeList[5]-1], data.nodesList[data.elementList[i].nodeList[6]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[1]-1], data.nodesList[data.elementList[i].nodeList[2]-1], data.nodesList[data.elementList[i].nodeList[6]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[2]-1], data.nodesList[data.elementList[i].nodeList[3]-1], data.nodesList[data.elementList[i].nodeList[7]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[4]-1], data.nodesList[data.elementList[i].nodeList[6]-1], data.nodesList[data.elementList[i].nodeList[7]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[4]-1], data.nodesList[data.elementList[i].nodeList[5]-1], data.nodesList[data.elementList[i].nodeList[6]-1]);
                drawTriangle(data.nodesList[data.elementList[i].nodeList[2]-1], data.nodesList[data.elementList[i].nodeList[6]-1], data.nodesList[data.elementList[i].nodeList[7]-1]);
            }


//            data.elementList.forEach( function(element){
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[1]], data.nodesList[element.nodeList[2]]);
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[2]], data.nodesList[element.nodeList[3]]);
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[3]], data.nodesList[element.nodeList[7]]);
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[4]], data.nodesList[element.nodeList[7]]);
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[1]], data.nodesList[element.nodeList[5]]);
//                drawTriangle(data.nodesList[element.nodeList[0]], data.nodesList[element.nodeList[4]], data.nodesList[element.nodeList[5]]);
//                drawTriangle(data.nodesList[element.nodeList[1]], data.nodesList[element.nodeList[5]], data.nodesList[element.nodeList[6]]);
//                drawTriangle(data.nodesList[element.nodeList[1]], data.nodesList[element.nodeList[2]], data.nodesList[element.nodeList[6]]);
//                drawTriangle(data.nodesList[element.nodeList[2]], data.nodesList[element.nodeList[3]], data.nodesList[element.nodeList[7]]);
//                drawTriangle(data.nodesList[element.nodeList[4]], data.nodesList[element.nodeList[6]], data.nodesList[element.nodeList[7]]);
//                drawTriangle(data.nodesList[element.nodeList[4]], data.nodesList[element.nodeList[5]], data.nodesList[element.nodeList[6]]);
//                drawTriangle(data.nodesList[element.nodeList[2]], data.nodesList[element.nodeList[6]], data.nodesList[element.nodeList[7]]);
//            })

            console.log("done");




//            var points = [
//                {x: -0.70711, y: 0, z: 0, color: 0x0000ff},
//                {x: -0.565689, y: -0.141421, z: 0, color: 0x0000ff},
//                {x: -0.424267, y: 0, z: 0, color: 0x0000ff},
//                {x: -0.565689, y: 0.141421, z: 0, color: 0x0000ff},
//                {x: -0.70711, y: 0, z: 0.2, color: 0x0000ff},
//                {x: -0.565689, y: -0.141421, z: 0.2, color: 0x0000ff},
//                {x: -0.424267, y: 0, z: 0.2, color: 0x0000ff},
//                {x: -0.565689, y: 0.141421, z: 0.2, color: 0x0000ff}
//            ];

        //    var i,j,k;
        //    for(i=0; i<points.length; i++){
        //        for(j=i+1; j<points.length; j++){
        //            for(k=j+1; k<points.length; k++){
        //                drawTriangle(points[i], points[j], points[k]);
        //            }
        //        }
        //    }

//            drawTriangle(points[0], points[1], points[2]);
//            drawTriangle(points[0], points[2], points[3]);
//            drawTriangle(points[0], points[3], points[7]);
//            drawTriangle(points[0], points[4], points[7]);
//            drawTriangle(points[0], points[1], points[5]);
//            drawTriangle(points[0], points[4], points[5]);
//            drawTriangle(points[1], points[5], points[6]);
//            drawTriangle(points[1], points[2], points[6]);
//            drawTriangle(points[2], points[3], points[7]);
//            drawTriangle(points[4], points[6], points[7]);
//            drawTriangle(points[4], points[5], points[6]);
//            drawTriangle(points[2], points[6], points[7]);

        }

        function drawTriangle(a, b, c){
            var geom = new THREE.Geometry();
            var v1 = new THREE.Vector3(a.x,a.y,a.z);
            var v2 = new THREE.Vector3(b.x,b.y,b.z);
            var v3 = new THREE.Vector3(c.x,c.y,c.z);

            geom.vertices.push(v1);
            geom.vertices.push(v2);
            geom.vertices.push(v3);

            var face = new THREE.Face3(0, 1, 2);
            face.vertexColors[0] = new THREE.Color(a.temperature, 0, 1 - a.temperature);
            face.vertexColors[1] = new THREE.Color(b.temperature, 0, 1 - b.temperature);
            face.vertexColors[2] = new THREE.Color(c.temperature, 0, 1 - c.temperature);
            geom.faces.push(face);

            var face2 = new THREE.Face3(2, 1, 0);
            face2.vertexColors[2] = new THREE.Color(a.temperature, 0, 1 - a.temperature);
            face2.vertexColors[1] = new THREE.Color(b.temperature, 0, 1 - b.temperature);
            face2.vertexColors[0] = new THREE.Color(c.temperature, 0, 1 - c.temperature);
            geom.faces.push(face2);

            var mesh = new THREE.Mesh( geom, new THREE.MeshBasicMaterial( { vertexColors: THREE.VertexColors }));
            scene.add(mesh)
        }

        function animateScene(){
            requestAnimationFrame(animateScene);
            renderScene();
        }

        function renderScene(){
            renderer.render(scene, camera);
        }
    }
});