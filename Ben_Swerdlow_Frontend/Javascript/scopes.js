let a = 'a (let, global)';
const b = 'b (const, global)';
var c = 'c (var, global)';

function test() {
    let d = 'd (let, function/local)';
    const e = 'e (const, function/local)';
    var f = 'f (var, function/local)';
    console.log("Within Function (Local) Scope:");
    try{
        console.log(a);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(b);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(c);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(d);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(e);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(f);
    } catch(err) {
        console.log(err);
    }
}

test();

console.log("Within Global Scope:")
try{
    console.log(a);
} catch(err) {
    console.log(err);
}
try{
    console.log(b);
} catch(err) {
    console.log(err);
}
try{
    console.log(c);
} catch(err) {
    console.log(err);
}
try{
    console.log(d);
} catch(err) {
    console.log(err);
}
try{
    console.log(e);
} catch(err) {
    console.log(err);
}
try{
    console.log(f);
} catch(err) {
    console.log(err);
}

if (true){
    let g = 'g (let, block/local scope)';
    const h = 'h (const, block/local scope)';
    var i = 'i, (var, block/local scope)';
    console.log('Within Block Scope:');
    try{
        console.log(a);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(b);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(c);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(d);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(e);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(f);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(g);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(h);
    } catch(err) {
        console.log(err);
    }
    try{
        console.log(i);
    } catch(err) {
        console.log(err);
    }
}

console.log("Referencing block scope variables in Global:")
try{
    console.log(g);
} catch(err) {
    console.log(err);
}
try{
    console.log(h);
} catch(err) {
    console.log(err);
}
try{
    console.log(i);
} catch(err) {
    console.log(err);
}