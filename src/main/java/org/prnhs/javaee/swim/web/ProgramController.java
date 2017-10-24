package org.prnhs.javaee.swim.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.prnhs.javaee.swim.services.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Program Controller")
@RequestMapping("/program")
public class ProgramController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramController.class);
    
    @Autowired
    private ProgramService programService;
    
    @RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a program", notes = "A new program is created when a certain objective is given.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message ="OK", response = ProgramDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ProgramDto save(@RequestBody @Valid @NotNull(message = "{program.null}") ProgramDto dto){
        LOGGER.debug("Save method called in ProgramController. Saving program with this objective: {}", dto.getObjective());
        
        ProgramDto savedItem = programService.save(dto);
        Link link = ControllerLinkBuilder.linkTo(ProgramController.class).slash(savedItem.getKey()).withSelfRel();
        savedItem.add(link);
        return savedItem;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve a program", notes = "Retrieve a program by it's id.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message ="OK", response = ProgramDto.class),
        @ApiResponse(code = 400, message = "Bad Request")})
    public ProgramDto getById(@PathVariable Integer id){
        LOGGER.debug("getById method called in ProgramController with the id value: {}", id);
        
        ProgramDto dto = programService.getById(id);
        Link link = ControllerLinkBuilder.linkTo(ProgramController.class).slash(dto.getKey()).withSelfRel();
        dto.add(link);
        
        return dto;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all programs", notes = "Get all the programs from the database.")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message ="OK", response = ProgramDto.class)})
    public List<ProgramDto> getAll(){
        LOGGER.debug("getAll method called in ProgramController class");
        
        List<ProgramDto> programs = programService.getAll();
        programs.stream().forEach((program) -> {
            program.add(ControllerLinkBuilder.linkTo(ProgramController.class).slash(program.getKey()).withSelfRel());
        });
        
        return programs;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a program", notes ="Find and delete the program with the given id.")
    public void delete(@PathVariable Integer id){
        LOGGER.debug("Delete method called in ProgramController with the id value: {}", id);
        programService.delete(id);
    }
}
