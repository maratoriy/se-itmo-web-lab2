const sc = new SquareCanvas("#graph");
const tm = document.querySelector("#history")
const form = document.querySelector("#request-form");

// Gather com.moratorium.data form our form
function gather() {
    let data = formDataToObject(new FormData(form));
    data.y = data.y.replace(",", ".")
    return data
}


// Draw the graph with labels
function draw({r}) {
    sc.updateArea();
    sc.ctx.lineWidth = 3;
    sc.shape("#d4bfd0", "#702963",
        50, 50,
        {type: "lineTo", x: 70, y: 50},
        {type: "arcTo", x1: 70, y1: 30, x2: 50, y2: 30, radius: 20},
        {type: "lineTo", x: 50, y: 10},
        {type: "lineTo", x: 30, y: 10},
        {type: "lineTo", x: 30, y: 50},
        {type: "lineTo", x: 10, y: 50},
        {type: "lineTo", x: 50, y: 70},
        {type: "lineTo", x: 50, y: 50}
    )
    sc.ctx.lineWidth = 1;

    let R = (isNaN(r / 1)) ? "R" : r;
    let R2 = (isNaN(r / 2)) ? "R/2" : r / 2;

    sc.line(0, 50, 100, 50); // Ox
    sc.line(50, 0, 50, 100); // Oy

    sc.line(10, 48.5, 10, 51.5); // | -R
    sc.fillText(`-${R}`, 11, 48.5, 0.8);

    sc.line(30, 48.5, 30, 51.5); // | -R/2
    sc.fillText(`-${R2}`, 31, 48.5, 0.8);

    sc.line(90, 48.5, 90, 51.5); // | R
    sc.fillText(`${R}`, 91, 48.5, 0.8);

    sc.line(70, 48.5, 70, 51.5); // | R/2
    sc.fillText(`${R2}`, 71, 48.5, 0.8);

    sc.line(48.5, 10, 51.5, 10); // - R
    sc.fillText(`${R}`, 52, 11, 0.8);

    sc.line(48.5, 30, 51.5, 30); // - R/2
    sc.fillText(`${R2}`, 52, 31, 0.8);

    sc.line(48.5, 70, 51.5, 70); // - -R/2
    sc.fillText(`-${R2}`, 52, 71, 0.8);


    sc.line(48.5, 90, 51.5, 90); // - -R
    sc.fillText(`-${R}`, 52, 91, 0.8);

    sc.line(48.5, 3, 50, 0);  // /\
    sc.line(51.5, 3, 50, 0);  // ||
    sc.fillText("y", 45, 4);

    sc.line(97, 51.5, 100, 50);
    sc.line(97, 48.5, 100, 50); // ->
    sc.fillText("x", 95, 47);

    dotArray.forEach((dot, index) => {
        sc.fillText(`${dotArray.length-index}`, dot.x+0.5, dot.y-0.5, 0.8);
        sc.dot(dot.x, dot.y, "#702963")
    })
}

// Validate dot (depending on source), then sends "POST" request, after which updates the content of table
function submit({x, y, r}, graphMode = false) {
    const check = (e, condition) => {
        (condition) ? e.style.color = "black" : e.style.color = "red";
        return condition
    }

    let valid = check(document.querySelector("#r-label"), r && !isNaN(r))
    if (!graphMode) {
        valid = check(document.querySelector("#y-label"), y !== '' && !isNaN(y) && y >= -5 && y <= 5) && valid
        valid = check(document.querySelector("#x-label"), x !== '' && !isNaN(x)) && valid
    } else {
        document.querySelector("#y-label").style.color="black";
        document.querySelector("#x-label").style.color="black";
    }
    if (valid) {
        post_request(`${contextPath}`, (html) => {
                tm.innerHTML = html;
            },
            {x, y, r, function: "check", dot: graphMode});
    }
    return valid
}

let dotArray = [];

// Sends request on click
sc.onclick = (e) => {
    let dot = gather();
    dot.x = (e.x / 100 - 0.5) * 10 / 4 * dot.r;
    dot.y = (-e.y / 100 + 0.5) * 10 / 4 * dot.r;
    if (submit(dot, true)) {
        if(dotArray.length>=10)
            dotArray = dotArray.slice(1,dotArray.length)
        dotArray.push({x: e.x, y: e.y})
        draw(gather())
    }
}

// Draw on load
window.addEventListener("load", () => {
    if(localStorage.getItem("dots"))
        dotArray = JSON.parse(localStorage.getItem("dots"))
    draw(gather())
})

window.addEventListener("unload", () =>
    localStorage.setItem("dots", JSON.stringify(dotArray))
)

// Redraw on resize
window.addEventListener("resize", () => draw(gather()));

// Redraw on change of "R"
form.querySelectorAll("input[name=r]")
    .forEach(input =>
        input.addEventListener("change", () => {
            dotArray = []
            draw(gather())
            draw(gather())
        }))


// Send "clear" request
document.querySelector("#clear-request")
    .addEventListener("click", () =>
        post_request(`${contextPath}`, (content) => {
            tm.innerHTML = content
            dotArray = []
            draw(gather())
        }, {function: "clear"}));

// Send "check" submit on submit
form.addEventListener("submit", (e) => {
        e.preventDefault();
        submit(gather());
    })


