$('.collapse li').click ->
  $(this).siblings('li').removeClass 'active'
  $(this).addClass 'active'
  return