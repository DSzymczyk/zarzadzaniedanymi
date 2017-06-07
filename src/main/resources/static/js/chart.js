var container, camera, scene, renderer, mesh,

				mouse = { x: 0, y: 0 },
				objects = [],

				count = 0,

				CANVAS_WIDTH = 950,
				CANVAS_HEIGHT = 900;

			canvas = document.getElementById( 'mycanvas' );

			renderer = new THREE.WebGLRenderer();
			//renderer.domElement = canvas;
			renderer.setSize( CANVAS_WIDTH, CANVAS_HEIGHT );
      canvas.appendChild( renderer.domElement );

			scene = new THREE.Scene();

			camera = new THREE.PerspectiveCamera( 50, CANVAS_WIDTH / CANVAS_HEIGHT, 1, 5000 );
			camera.position.y = 150;
			camera.position.z = 500;
			camera.lookAt( scene.position );

			mesh = new THREE.Mesh(
				new THREE.BoxGeometry( 200, 200, 200, 1, 1, 1 ),
				new THREE.MeshBasicMaterial( { color : 0xff0000, wireframe: true }
			) );
			scene.add( mesh );
			objects.push( mesh );


			// find intersections
			var vector = new THREE.Vector3();
			var raycaster = new THREE.Raycaster();

			// mouse listener
			document.addEventListener( 'mousedown', function( event ) {

				// For the following method to work correctly, set the canvas position *static*; margin > 0 and padding > 0 are OK
				mouse.x = ( ( event.clientX - renderer.domElement.offsetLeft ) / renderer.domElement.width ) * 2 - 1;
				mouse.y = - ( ( event.clientY - renderer.domElement.offsetTop ) / renderer.domElement.height ) * 2 + 1;

				// For this alternate method, set the canvas position *fixed*; set top > 0, set left > 0; padding must be 0; margin > 0 is OK
				//mouse.x = ( ( event.clientX - container.offsetLeft ) / container.clientWidth ) * 2 - 1;
				//mouse.y = - ( ( event.clientY - container.offsetTop ) / container.clientHeight ) * 2 + 1;

				vector.set( mouse.x, mouse.y, 0.5 );
				vector.unproject( camera );

				raycaster.set( camera.position, vector.sub( camera.position ).normalize() );

				intersects = raycaster.intersectObjects( objects );

				if ( intersects.length > 0 ) {
					console.log("hit");
				}

			}, false );

			function render() {

				mesh.rotation.y += 0.01;

				renderer.render( scene, camera );

			}

			(function animate() {

				requestAnimationFrame( animate );

				render();

			})();