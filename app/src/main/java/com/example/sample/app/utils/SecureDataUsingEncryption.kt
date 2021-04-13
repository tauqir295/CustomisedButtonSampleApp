package com.example.sample.app.utils

import org.apache.commons.codec.binary.Base64
import java.nio.charset.StandardCharsets
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class SecureDataUsingEncryption {
    private var dcipher: Cipher
    private var salt = "12345678".toByteArray()
    var iterationCount = 1024
    private var keyStrength = 256
    private var key: SecretKey
    private lateinit var iv: ByteArray

    @Throws(Exception::class)
    fun encrypt(data: String): String {
        dcipher.init(Cipher.ENCRYPT_MODE, key)
        val params = dcipher.parameters
        iv = params.getParameterSpec(IvParameterSpec::class.java).iv
        val utf8EncryptedData = dcipher.doFinal(data.toByteArray())
        val base64EncryptedData = Base64.encodeBase64String(utf8EncryptedData)
        println("Encrypted Data $base64EncryptedData")
        return base64EncryptedData
    }

    @Throws(Exception::class)
    fun decrypt(base64EncryptedData: String?): String {
        dcipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
        val decryptedData = Base64.decodeBase64(base64EncryptedData)
        val utf8 = dcipher.doFinal(decryptedData)
        return String(utf8, StandardCharsets.UTF_8)
    }

    companion object {
        const val SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA1"
        const val SECRET_KEY_SPEC = "AES"
        const val TRANSFORMATION = "AES/CBC/PKCS5Padding"
        const val PASS_PHRASE = "Change the pass phrase as per need"
    }

    init {
        val factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM)
        val spec: KeySpec = PBEKeySpec(PASS_PHRASE.toCharArray(), salt, iterationCount, keyStrength)
        val secretKey = factory.generateSecret(spec)
        key = SecretKeySpec(secretKey.encoded, SECRET_KEY_SPEC)
        dcipher = Cipher.getInstance(TRANSFORMATION)
    }
}
