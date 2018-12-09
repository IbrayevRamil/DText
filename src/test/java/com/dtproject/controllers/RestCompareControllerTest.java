package com.dtproject.controllers;

import com.dtproject.configuration.AppConfig;
import com.dtproject.dtos.CompareDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class RestCompareControllerTest {

    private final static String SOURCE_FILE = "testOriginal.docx";
    private final static String TARGET_FILE = "testRevised.docx";
    private final static String MODE = "strict";
    private final static String FORMAT = "inline";
    private final static String EXPECTED_CONTENT =
            "Match percentage: 75.0%\n" +
                    "[Agreement->Compliment] interested [discretion->foundation] estimating on stimulated[+oh.]  Dear sing when find\n" +
                    "[READ OF CALL.->read of call.] As distrusts behaviour defective is. Never at water [me->po] might. On [+why] formed merits\n" +
                    "hunted unable merely by mr whence why or. Possession unpleasing simplicity her [gyy.->uncommonly.]\n" +
                    "[-Dasdsadasdasd] \n" +
                    "[-Dasdsadsadsa] \n";


    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void compareGet() throws Exception {
        mockMvc.perform(get("/compare")
                .param("source", SOURCE_FILE)
                .param("target", TARGET_FILE)
                .param("mode", MODE)
                .param("format", FORMAT).contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string(EXPECTED_CONTENT));
    }

    @Test
    public void comparePost() throws Exception {
        CompareDto dto = new CompareDto(SOURCE_FILE, TARGET_FILE, MODE, FORMAT);
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = mapper.writeValueAsString(dto);
        mockMvc.perform(post("/compare").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonValue))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string(EXPECTED_CONTENT));
    }

    @Test
    public void comparePostFile() throws Exception {

        FileInputStream sourceStream = new FileInputStream(new File(SOURCE_FILE));
        FileInputStream targetStream = new FileInputStream(new File(TARGET_FILE));
        MockMultipartFile source = new MockMultipartFile("source", SOURCE_FILE,
                "multipart/form-data", sourceStream);
        MockMultipartFile target = new MockMultipartFile("target", TARGET_FILE,
                "multipart/form-data", targetStream);
        String resultFilePath = System.getProperty("java.io.tmpdir") + "/result.pdf";
        Path resultFile = Paths.get(resultFilePath);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/compare")
                .file(source)
                .file(target)
                .param("mode", "strict")
                .param("format", "inline"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(content().bytes(Files.readAllBytes(resultFile)));
    }

}