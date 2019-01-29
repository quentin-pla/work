import { post } from '../../javascript/post.js';

var p = document.getElementById("p").value;

var id = document.getElementById("id").value;

console.log("page: ",p," id:",id);

var feedback = function(res) {
    if (res.success === true) {
        var get_link = res.data.link.replace(/^http:\/\//i, 'https://');
        post('index.php', {p: p,link: get_link,id: id});
    }
};

new Imgur({
    clientid: 'eba443ef58e582b', //You can change this ClientID
    callback: feedback
});