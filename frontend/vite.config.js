import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      '/api':    { target: 'http://localhost:80', changeOrigin: true },
      '/user':   { target: 'http://localhost:80', changeOrigin: true },
      '/show':   { target: 'http://localhost:80', changeOrigin: true },
      '/book':   { target: 'http://localhost:80', changeOrigin: true },
      '/review': { target: 'http://localhost:80', changeOrigin: true },
      '/main':   { target: 'http://localhost:80', changeOrigin: true },
      '/static': { target: 'http://localhost:80', changeOrigin: true },
    },
  },
});
