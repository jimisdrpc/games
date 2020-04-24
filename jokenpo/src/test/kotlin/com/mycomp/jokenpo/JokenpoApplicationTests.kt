package com.mycomp.jokenpo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mycomp.jokenpo.controller.UserController
import com.mycomp.jokenpo.model.User
import com.mycomp.jokenpo.respository.UserRepository
import com.mycomp.jokenpo.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class JokenpoApplicationTests {

	@Autowired
	lateinit var testRestTemplate: TestRestTemplate

	@Autowired
	private lateinit var mvc: MockMvc

	@InjectMocks
	lateinit var controller: UserController

	@Mock
	lateinit var respository: UserRepository

	@Mock
	lateinit var service: UserService

	//private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

	@BeforeEach
	fun setup() {
		MockitoAnnotations.initMocks(this)
		mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
		`when`(respository.save(User(1, "Test")))
				.thenReturn(User(1, "Test"))

	}

	@Test
	fun createUser() {
		//val created = MockMvcResultMatchers.status().isCreated

		var user = User(2, "Test")
		var jsonData = jacksonObjectMapper().writeValueAsString(user)
		mvc.perform(MockMvcRequestBuilders.post("/users/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData))
				.andExpect(MockMvcResultMatchers.status().isOk)
				//.andExpect(created)
				.andDo(MockMvcResultHandlers.print())
				.andReturn()
	}

	@Test
	fun findUser() {

		val ok = MockMvcResultMatchers.status().isOk

		val builder = MockMvcRequestBuilders.get("/users?id=99")
		this.mvc.perform(builder)
				.andExpect(ok)


	}

}