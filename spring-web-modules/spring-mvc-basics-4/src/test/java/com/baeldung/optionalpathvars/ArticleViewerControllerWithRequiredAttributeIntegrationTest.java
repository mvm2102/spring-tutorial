package com.baeldung.optionalpathvars;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.baeldung.validation.listvalidation.SpringListValidationApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringListValidationApplication.class)
public class ArticleViewerControllerWithRequiredAttributeIntegrationTest {

    @Autowired
    private WebApplicationContext wac;
 
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenRequiredAttributeIsFalse_whenIdPathVariableIsPassed_thenResponseOK() throws Exception {
    
        int articleId = 154;
        
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/requiredAttribute/article/{id}", articleId))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(articleId));
                
    }
    
    @Test
    public void givenRequiredAttributeIsFalse_whenIdPathVariableIsNotPassed_thenResponseOK() throws Exception {
            
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/requiredAttribute/article"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Article.DEFAULT_ARTICLE.getId()));
                
    }
}