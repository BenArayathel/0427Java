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

        console.log("easyHttp is running ...");


        return new Promise((resolve, reject) => {

            console.log("easyHttp Promise is running ...");

            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(data)
            })
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
            })
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
            })
                .then(res => res.json())
                .then(() => resolve('Resource Deleted...'))
                .catch(err => reject(err));
        });

    }
}

function easy1HTTP() {
    this.http = new XMLHttpRequest();
}

// MAKE AN HTTP GET REQUEST
easy1HTTP.prototype.get = function (url, callback) {
    this.http.open('GET', url, true);

    let self = this;
    this.http.onload = function() {

        if (self.http.status === 200) {

            //console.log(self.http.responseText);
            callback(null, self.http.responseText);
            
        } else {
            callback('Error: ' + self.http.status);
        }
    }

    this.http.send();
}