const http = new easyHTTP;
let thePosts = [];

// GET POSTS
//http.get('https://jsonplaceholder.typicode.com/posts');

// 'https://jsonplaceholder.typicode.com/posts'
http.get('https://jsonplaceholder.typicode.com/posts?_limit=5', 
function (err, posts) {

    let output = '';
    if (err) {
        console.log(err);
        output += '<li>Something went wrong</li>'
    } else {

        console.log(posts);
        thePosts = JSON.parse(posts);
        console.log("thePosts.length in GET: " + thePosts.length);
        for(const post of thePosts) {
            // output += "<li>"+post.title+"</li>";
            output +=
            `<div class="card my-1">
                <div class="card-header bg-light">
                    <div class="row">
                        <div class="col-6">
                            <h6 class="text-center">${post.id}</h6>
                        </div>
                        <div class="col-6">
                            <h6 class="text-center">${post.userId}</h6>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <p class="text-center">${post.title}</p>
                </div>
                <div class="card-footer">
                <button onclick="theDelete(${post.id})" class="btn btn-danger">Delete</button>
                </div>
            </div>`;
        }

        
    }

    document.querySelector('.myPosts').innerHTML = output;
    
});




// CREATE DATA
const data = {
    title: 'Custom Post', body: 'This is a custom post'
};



/**
 * I am suprpised at how SLOW THIS IS, either jasonplaceholder is slow today, or something
 * with my code is incorrect.  ?? ?? ?? ??
 * @param {*} id 
 */
// DELETE
function theDelete(id) {

    http.delete(`https://jsonplaceholder.typicode.com/posts/${id}`, 
    function (err, response) {

        if (err) {
            console.log(err);
        } else {

            for (var i = 0; i < thePosts.length; i++) { // const p of thePosts
                
                if (thePosts[i].id == id) {   // p.id == id

                    thePosts.splice(i, 1);
                }
            }
            print(thePosts);
            console.log(response);
        }
    }); 
}

/**
 * After manipulation of the list, manage local state, and append updated data to DOM
 * @param {*} localPosts 
 */
function print(localPosts) {
    
    let output = "";
    for(const post of localPosts) {
        // output += "<li>"+post.title+"</li>";
        output +=
        `<div class="card my-1">
            <div class="card-header bg-light">
                <div class="row">
                    <div class="col-6">
                        <h6 class="text-center">${post.id}</h6>
                    </div>
                    <div class="col-6">
                        <h6 class="text-center">${post.userId}</h6>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <p class="text-center">${post.title}</p>
            </div>
            <div class="card-footer">
                <button onclick="theDelete(${post.id})" class="btn btn-danger">Delete</button>
            </div>
        </div>`;
    }
    document.querySelector('.myPosts').innerHTML = output;
}
