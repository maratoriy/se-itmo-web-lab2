
/*
   Construction
       "<div class="button_toggle_group">
            <button></button>
            <button></button>
            <button></button>
       </div>"
    acts like group of radio buttons (toggled have class "active")
 */
document.querySelectorAll(".button-group")
    .forEach((group) => {
            let buttons = group.querySelectorAll("button.button-group__button, input[type=button].button-group__button");
            buttons.forEach((button) => {
                button.addEventListener("click", () => {
                    buttons.forEach((e) => e.classList.remove("active"));
                    button.classList.add("active");
                })
            })
        }
    )

/*
    <button> or <input type="button">  will set value to specific input (with the same name)
 */

document.querySelectorAll("form").forEach((form) =>
    form.querySelectorAll("button.value-button, input[type=button].value-button")
        .forEach(button => button.addEventListener("click", () => {
            let value = button.value
            form.querySelectorAll(`.value-button__input[name=${button.attributes.getNamedItem("name").value}]`)
                .forEach(input => {
                    input.value = value
                    input.dispatchEvent(new Event("change"))
                })
        })))




/*
    Save <input class="save-value"> from <form id="%" class="savable-form"> to local storage:
        %: JSON
 */
window.addEventListener("beforeunload", () =>
    document.querySelectorAll("form.savable-form").forEach((form) => {
        let saveObj = {}
        form.querySelectorAll("input:not([type=button]).save-value").forEach(input => {
            let value = input.value
            let name = input.getAttribute("name")
            if (value && name) {
                saveObj[name] = value;
            }
        })
        localStorage.setItem(form.id, JSON.stringify(saveObj));
    }))

/*
    Load <input class="save-value"> and <button class="value-button"> to <form id="%" class="savable-form"> from local storage:
        %: JSON
 */
window.addEventListener("load", () => {
    document.querySelectorAll("form.savable-form").forEach((form) => {
        let json = localStorage.getItem(form.id)
        if(json) {
            let loadObj = JSON.parse(json)
            for(let key in loadObj) {
                let value = loadObj[key]
                if(value) {
                    form.querySelectorAll(`input[name=${key}]:not([type=button]).save-value`).forEach((input) => {
                        input.value = value
                    })
                    form.querySelectorAll(`.value-button[name=${key}][value='${value}']`)
                        .forEach(button => button.classList.add("active"))
                }
            }
        }
    })
})



/*
    buttons with class .reset_form will set to "" (or default value) all inputs and unload saved com.moratorium.data from localstorage
 */
document.querySelectorAll("button.reset-form, input[type=button].reset-form")
    .forEach(button => button.addEventListener("click", () => {
        let form = button.closest("form")
        form.querySelectorAll("input:not([type=button]):not([type=submit])").forEach(input => {
            input.value = ""
            if (input.classList.contains("save-value")) {
                let defaultValue = input.getAttribute("default")
                if (defaultValue) {
                    input.value = defaultValue
                    input.dispatchEvent(new Event("change"))
                }
                window.localStorage.removeItem(input.getAttribute("name"))
            }
        })
        form.querySelectorAll("button, input[type=button]").forEach(button => button.classList.remove("active"))
    }))


document.querySelectorAll("form")
    .forEach((form) => {
        let reset = form.querySelector(".reset-form");
        if(reset)
            reset.addEventListener("click", () =>
                form.querySelectorAll(".input-group__header")
                    .forEach((header) =>
                        header.style.color = "black"))
    })
