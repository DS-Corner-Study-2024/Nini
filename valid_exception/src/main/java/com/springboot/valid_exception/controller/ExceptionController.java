package com.springboot.valid_exception.controller;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    private final Logger LOGGER=LoggerFactory.getLogger(ExceptionController.class);

    @GetMapping
    public void getRuntimeException(){
        throw new RuntimeException("getRuntimeException 메서드 호출");
    }

    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e,
                                                               HttpServletRequest request){
        HttpHeaders responseHeaders=new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

        LOGGER.error("클래스 내 handleException 호출, {}, {}", request.getRequestURI(),
                e.getMessage());

        Map<String, String> map=new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code","400");
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
