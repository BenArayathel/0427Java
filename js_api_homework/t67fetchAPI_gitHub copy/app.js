document.getElementById('button1').addEventListener('click', getText);
document.getElementById('button2').addEventListener('click', getJson);
document.getElementById('button3').addEventListener('click', getExternal);

// Get local text data
function getText() {
    fetch('test.txt').then(function(res) {
        return res.text();
        
    })
    .then(function (data) {
        console.log(data);
        document.getElementById('output').innerHTML = data;
    })
    .catch(function (err) {
        console.log(err);
        
    });
}

// Get local JSON data
function getJson() {
    fetch('posts.json').then(function(res) {
        return res.json();
        
    })
    .then(function (data) {
        console.log(data);
        let output = '';

        data.forEach(function(post) {
            output += `<li>${post.title}</li>`
        });
        document.getElementById('output2').innerHTML = output;
    })
    .catch(function (err) {
        console.log(err);
        
    });
}

// Get remote API data
// https://api.github.com/users
function getExternal() {
    fetch('https://api.github.com/users').then(function(res) {
        return res.json();
        
    })
    .then(function (data) {
        console.log(data);
        let output = '';

        data.forEach(function(user) {
            // output += `<li>${user.login}</li>`;
            output += 
            `<tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.login}</td>
                    <td>${user.node_id}</td>
                    <td><img width="50px;" src="${user.avatar_url}" alt=""></td>
            </tr>`;
        });
        document.getElementById('output3').innerHTML = output;
    })
    .catch(function (err) {
        console.log(err);
        
    });
}

// document.querySelector('.jokes').innerHTML = output;