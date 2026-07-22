package com.feragusper.buenosairesantesydespues.core.network.mapper

import com.feragusper.buenosairesantesydespues.core.network.dto.AttachmentDto
import com.feragusper.buenosairesantesydespues.core.network.dto.CustomFieldsDto
import com.feragusper.buenosairesantesydespues.core.network.dto.HistoricalRecordDto
import com.feragusper.buenosairesantesydespues.core.network.dto.ImageDto
import com.feragusper.buenosairesantesydespues.core.network.dto.ImagesDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class HistoricalRecordMapperTest {

    @Test
    fun `maps custom fields, geo and before-after images to the domain model`() {
        val dto = HistoricalRecordDto(
            id = 42,
            title = "Obelisco",
            customFields = CustomFieldsDto(
                creditsNow = listOf("Gastón - AGN"),
                description = listOf("Descripción"),
                yearBefore = listOf("1936"),
                address = listOf("9 de Julio"),
                neighborhood = listOf("San Nicolás"),
                geo = listOf("-34.6037,-58.3816"),
            ),
            attachments = listOf(
                AttachmentDto(
                    title = "foto antes",
                    images = ImagesDto(
                        thumbnail = ImageDto("http://x/thumb_before.jpg"),
                        full = ImageDto("http://x/before.jpg"),
                    ),
                ),
                AttachmentDto(
                    title = "foto ahora",
                    images = ImagesDto(full = ImageDto("http://x/after.jpg")),
                ),
            ),
        )

        val record = dto.toDomain()

        assertEquals("42", record.id)
        assertEquals("Obelisco", record.title)
        // " - " becomes a newline (legacy behavior).
        assertEquals("Gastón\nAGN", record.credits)
        assertEquals(-34.6037, record.lat, 0.0)
        assertEquals(-58.3816, record.lng, 0.0)
        assertEquals("http://x/before.jpg", record.imageUrlBefore)
        assertEquals("http://x/after.jpg", record.imageUrlAfter)
        assertEquals("http://x/thumb_before.jpg", record.thumbnailUrl)
    }

    @Test
    fun `handles missing geo and attachments without crashing`() {
        val record = HistoricalRecordDto(id = 1).toDomain()

        assertEquals("1", record.id)
        assertEquals(0.0, record.lat, 0.0)
        assertEquals(0.0, record.lng, 0.0)
        assertNull(record.imageUrlBefore)
        assertNull(record.imageUrlAfter)
    }
}
