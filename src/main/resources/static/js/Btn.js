const toggleBtn = document.querySelector('.navbar__toggleBtn');
const menu = document.querySelector('.navbar__menu');
const chatbot = document.getElementById('message-box');
const chatbotbutton = document.getElementById('chatbot-button')

toggleBtn.addEventListener('click', () => {
    menu.classList.toggle('active');
});

chatbotbutton.addEventListener('click', function() {
    if (chatbot.style.display == 'none') {
        chatbot.style.display = 'block';
    } else {
        chatbot.style.display = 'none';
    }
});