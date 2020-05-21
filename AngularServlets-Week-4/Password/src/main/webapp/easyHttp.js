/**
 * Library for making HTTP requests
 * 
 * this will be using: https://jsonplaceholder.typicode.com
 * /users
 * 
 * uses Fetch & Promise's
 */

class EasyHttp {


    // GET http request
    get(url) {

       return new Promise((resolve, reject) => {

           fetch(url)
               .then(res => res.json())
               .then(data => resolve(data))
               .catch(err => reject(err));
       });

    }

    // POST http requst
    post(url, data) {

       return new Promise((resolve, reject) => {

           fetch(url, {
               method: 'POST',
               headers: {
                   'Content-type': 'application/json'
               },
               body: JSON.stringify(data)
           } )
               .then(res => res.json())
               .then(data => resolve(data))
               .catch(err => reject(err));
       });

    }

         // PUT http requst
         put(url, data) {

           return new Promise((resolve, reject) => {
   
               fetch(url, {
                   method: 'PUT',
                   headers: {
                       'Content-type': 'application/json'
                   },
                   body: JSON.stringify(data)
               } )
                   .then(res => res.json())
                   .then(data => resolve(data))
                   .catch(err => reject(err));
           });
   
        }

                  // DELETE http requst
                  delete(url) {

                   return new Promise((resolve, reject) => {
           
                       fetch(url, {
                           method: 'DELETE',
                           headers: {
                               'Content-type': 'application/json'
                           }
                       } )
                           .then(res => res.json())
                           .then(() => resolve('Resource Deleted...'))
                           .catch(err => reject(err));
                   });
           
                }
}