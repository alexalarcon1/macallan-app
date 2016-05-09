
$ ->
  theForm = $('#login-form')

  theForm.on 'click', '.login-submit', (e) ->
    e.preventDefault()

    formData = {
      username: $('#login-form input[name=username]').val()
      password: $('#login-form input[name=password]').val()
    }

    $.ajax
      url: "/login"
      data: JSON.stringify(formData)
      type: "POST"
      contentType: "application/json"
      success: (data) ->
        console.log("You've logged in!")
        console.log(data)
        alert("Welcome!")
        window.location.replace("/home")
      error: (data) ->
        console.log("Error with submission")
        console.log(data)
        alert("Please enter valid username/password")

