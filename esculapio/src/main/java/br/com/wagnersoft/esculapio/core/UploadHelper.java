package br.com.wagnersoft.esculapio.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public final class UploadHelper {

  private static final String PATH = "C:\\Users\\wagner\\";

  public UploadHelper() throws Exception {
  }

  @SuppressWarnings("resource")
  public final File copyFile(final File arquivo, final String nome) throws Exception {
    FileChannel sourceChannel = null;
    FileChannel destinationChannel = null;
    try {
      final File out = new File(new StringBuilder(PATH).append(nome).toString());
      sourceChannel = new FileInputStream(arquivo).getChannel();
      destinationChannel = new FileOutputStream(out).getChannel();
      sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
      return out;
    } finally {
      if (sourceChannel != null) {
        try {
          sourceChannel.close();
        } finally {
          sourceChannel = null;
        }
      }
      if (destinationChannel != null) {
        try {
          destinationChannel.close();
        } finally {
          destinationChannel = null;
        }
      }
    }
  }

}
