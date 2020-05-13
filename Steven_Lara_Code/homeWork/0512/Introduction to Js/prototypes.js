/* Javascript is a Prototype-Based Language
    All objects contain the hidden [[Prototype]] property
        this is the equilvalent to inheritance in Object Oriented Languages
*/
function keyboard(name, size) {
    this.name = name;
    this.size = size;
};

function mechanicalKeyboard(name, size, RGB) {
    keyboard.call(this, name, size);

    this.RGB = RGB;
};

function wirelessKeyboard(name, size, wireless) {
    keyboard.call(this, name, size);

    this.wireless = wireless;
};

// Explicitly link Prototypes together

mechanicalKeyboard.prototype = Object.create(keyboard.prototype);
wirelessKeyboard.prototype = Object.create(keyboard.prototype);

// Creating Methods that will be inherited by child prototypes

keyboard.prototype.type = function () {
    return `The ${this.name} is typing!`;
}

mechanicalKeyboard.prototype.RGBon = function () {
    return `My RGB lights are ${this.RGB} and they are so bright...`;
}

wirelessKeyboard.prototype.wirelessOn = function () {
    return `Wireless Mode is currently ${this.wireless}...`;
}

// Instantiating mechanicalKeyboard and wirelessKeyboard

let mk = new mechanicalKeyboard("Mechanical Keyboard", "60%", "ON");
let wk = new wirelessKeyboard("Wireless Keyboard", "65%", "OFF");

// Outputting to the console

console.log(mk);
console.log(mk.type());
console.log(mk.RGBon());

console.log(wk);
console.log(wk.type());
console.log(wk.wirelessOn());