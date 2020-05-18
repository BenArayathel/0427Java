
// https://jsonplaceholder.typicode.com

function easyHTTP() {
    this.http = new XMLHttpRequest();
}

// MAKE AN HTTP GET REQUEST
easyHTTP.prototype.get = function (url, callback) {
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



// MAKE AN HTTP DELETE REQUEST
easyHTTP.prototype.delete = function (url, callback) {
    this.http.open('DELETE', url, true);

    let self = this;
    this.http.onload = function() {

        if (self.http.status === 200) {

            //console.log(self.http.responseText);
            callback(null, 'Post Deleted on Server');
            
        } else {
            callback('Error: ' + self.http.status);
        }
    }

    this.http.send();
}