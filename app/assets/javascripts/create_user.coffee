$ ->
  theForm = $('#new-user-form')

  theForm.submit (e) ->
    e.preventDefault()

  $.ajax
    url: "/addUser"
    data: data
    type: "POST"
    success: (data) ->
      $('#messages').removeClass('hide').addClass('alert alert-success alert-dismissible').slideDown().show()
      $('#messages_content').html('<h4>Success! New user was created.</h4>')
      $('#modal').modal('show')
      window.location.replace("/user/new")
    error: (data) ->
      console.log("Error with submission")
      console.log(data)
      alert("Please try again")
