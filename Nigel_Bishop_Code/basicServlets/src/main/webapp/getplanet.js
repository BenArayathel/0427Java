//GET DATA TEST

let planet = document.getElementById('getplanet');
//planet.addEventListener('click',getPlanet)
window.addEventListener("click",getPlanet)

//let output = document.getElementById('output');


//function getPlanet(){
//    fetch('http://localhost:9090/basicServlets/WelcomePageHome')
//    .then((res) => res.text())
//    .then((data) => {
//    	 document.getElementById('output').innerHTML = data;
//    })
//    .catch((err) => console.log(err)); 
//}


//function getPlanet(){
//    fetch('http://localhost:9090/basicServlets/WelcomePageHome')
//    .then((res) => res.text())
//    .then((data) => {
//        document.getElementById('output').innerHTML = data;
//    })
//    .catch((err) => console.log(err));  
//}

function getPlanet() {
    fetch('http://localhost:9090/basicServlets/WelcomePageHome')
        .then(response => {
             console.log(response)
            return response.json();
        })
        .then(json => {


            let custOutput = '';


                 console.log(json)
                for (let i in json) {
    
                  custOutput += `

                	  <tr>
                	  	<td> ${json[i].name} </td>
                	  	<td> ${json[i].moonname} </td>
                	  	<td> ${json[i].numberofmoon} </td>
                	  </tr>

                  `;

            }

  
                 console.log(custOutput);

                 document.getElementById('output2').innerHTML = custOutput;
        })
    }


//function getPlanet() {
//    fetch('http://localhost:9090/basicServlets/WelcomePageHome')
//        .then(response => {
//             console.log(response)
//            return response.json();
//        })
//        .then(json => {
//
//
//          let custOutput = '<h2 class="mb-4">PLANETS</h2>';
//                 console.log(json)   
//                  custOutput += `
//                  <ul class="list-group mb-3">
//                      <li class="list-group-item">Planet Name:    ${json.name}</li>
//                      <li class="list-group-item">Moon Name:    ${json.moonname}</li>
//                      <li class="list-group-item">Number of Moon:    ${json.numberofmoon}</li>
//                  </ul>          
//                  `;
//                  document.getElementById('output').innerHTML = custOutput;
//
//        })
//    }

